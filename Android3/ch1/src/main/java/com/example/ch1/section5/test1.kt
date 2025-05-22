package com.example.ch1.section5

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//channel.....................

fun main() = runBlocking {
    //발행자와 구독자가 공유할 채널 선언...
    val channel = Channel<Int>()

    //데이터 발행....
    launch {
        repeat(3){
            delay(100)
            channel.send(it)
            println("send $it")
        }
        channel.close()
    }

    //데이터 구독 1....
//    repeat(3){
//        delay(300)
//        println("receive : ${channel.receive()}")
//    }

    //데이터 구독 2....
//    for(data in channel){//channel close 될때까지....
//        println("receiver.. $data")
//    }

    //데이터 구독..3...
    channel.consumeEach {
        println("receive... $it")
    }
}