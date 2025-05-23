package com.example.ch1.section6

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//flow builder..................
val helloFlow = flow<String> {
    repeat(3){
        delay(100)
        emit("hello $it")
    }
}

val worldFlow = flow<String> {
    repeat(3){
        delay(200)
        emit("world $it")
    }
}

fun <T> Flow<T>.flowMerge(other: Flow<T>): Flow<T> = channelFlow {
    //Flow 를 리턴시키는 flow builder..
    //이 안쪽은 ChannelScope 에 의해 실행..... 코루틴...
    //데이터 발행을 send...
    val job1 = launch {
        collect { send(it) }//helloFlow
    }
    val job2 = launch {
        other.collect { send(it) }//worldflow
    }
    job1.join()
    job2.join()
}

fun main() = runBlocking {
    //데이터 몇개가 있는데.(Collection 타입으로 묶이지 않은 상태)이걸 flow 로 발행하고 싶을 때..
    flowOf(1, 2, "hello")
        .collect {
            println("flowOf : $it")
        }

    listOf(1,2).asFlow()
        .collect {
            println("asFlow : $it")
        }

    helloFlow.flowMerge(worldFlow).collect {
        println(it)
    }

}