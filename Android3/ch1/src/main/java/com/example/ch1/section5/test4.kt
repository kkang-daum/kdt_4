package com.example.ch1.section5

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//pipeline................ 여러 채널을 결함시켜서.. 하나의 데이터 흐름으로 만들어 처리..

fun CoroutineScope.somethingA() = produce<Int> {
    var x = 1
    while(true){
        delay(100)
        send(x++)
    }
}

//매개변수로 넘어오는 다른 channel 의 데이터를 받아서.. 새로운 데이터로 발행하겠다.. pipeline
fun CoroutineScope.somethingB(receiveChannel: ReceiveChannel<Int>) = produce<Int> {
    receiveChannel.consumeEach {
        //수신한 데이터를 핸들링하고.. 새로운 데이터로 발행..
        send(it * it)
    }
}

fun main() = runBlocking {
    val job = launch {
        val receiveChannelA = somethingA()
        val receiveChannelB = somethingB(receiveChannelA)

        receiveChannelB.consumeEach {
            println("receive : $it")
        }
    }
    delay(500)
    job.cancel()
}