package com.example.ch1.section5

import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//Channel 을 이용하는 coroutine builder... produce(), actor()

fun main() = runBlocking {

    //리턴 값은 이 코루틴에 의해 발행되는 데이터를 받기 위한 ReceiveChannel
    val receiveChannel = produce<Int>(capacity = 2) {
        //이 안쪽은.. 자동으로 ProductScope 가 준비도고 이 스코프 영역내에서 실행..
        //그래서 별도의 Channel 을 준비하지 않아도 send 가 가능..
        //이 builder 를 이용하고 있는 곳의 scope 를 그대로 상속받아서 ProductScope 가 준비
        repeat(5){
            send(it)
            println("send $it")
            delay(100)
        }
    }

    receiveChannel.consumeEach {
        println("receive $it")
        delay(200)
    }
    //send 0
    //receive 0
    //send 1
    //receive 1
    //send 2
    //send 3
    //receive 2
    //send 4
    //receive 3
    //receive 4


    //데이터를 수신해서 업무를 돌리는 코루틴.. 빌더...
    val sendChannel = actor<Int> {
        //ActorScope 에 의해 실행.. 수신 준비..
        //리턴은.. SendChannel
        consumeEach {
            println("receive : $it")
        }
    }
    val job = launch {
        repeat(3){
            delay(100)
            sendChannel.send(it)
        }
    }
    job.join()
    sendChannel.close()
    println()

}