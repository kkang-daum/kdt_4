package com.example.ch1.section5

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//Channel.... buffer 이용....
//발행과 구독이 별개임으로.. 둘간의 속도차이가 발생할 수 있다..
//기본은 발행해야 구독, 구독해야 발행... 미리 움직일 수는 없다..
//buffer 개념을 적용해서 구독이 완료되지 않았다고 하더라도 미리 발행시켜 놓을 수 있다..

fun main() = runBlocking {
    //test1... buffer 개념 없는 경우...
//    val channel = Channel<Int>()
    //send 0
    //receive 0
    //receive 1
    //send 1
    //receive 2
    //send 2
    //receive 3
    //send 3
    //receive 4
    //send 4

    //test2... 버퍼 적용.... 수신을 하지 않았다고 하더라도 미리 발행할 수 있다..
    //버퍼에 여유가 없으면 더이상 발행이 되지 않고 대기하게 된다..
    //버퍼 여유 만큼
//    val channel = Channel<Int>(capacity = 2)
    //send 0
    //receive 0 []
    //send 1
    //receive 1
    //send 2
    //send 3 [2, 3]
    //receive 2
    //send 4
    //receive 3
    //receive 4

    //test3.... 버퍼가 다 찼다고 하더라도.. 계속 데이터를 발행하고 싶으면..
    //이전에 발행했던 데이터를 덮어쓰게 해야 한다..
    //구독측에서 발행했던 데이터중.. 구독하지 못하는 데이터가 있을 수도 있다..
    val channel = Channel<Int>(capacity = 2, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    //수신을 보면.. 2번을 수신하지 못하고 있다..
    //send 0
    //receive 0 []
    //send 1
    //send 2 [1, 2]
    //receive 1 [2]
    //send 3 [2,3]
    //send 4 [3,4]
    //receive 3
    //receive 4

    launch {
        repeat(5){
            channel.send(it)
            println("send $it")
            delay(200)
        }
        channel.close()
    }

    for(data in channel){
        println("receive $data")
        delay(400)
    }
}