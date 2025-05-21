package com.example.ch1.section1


import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

//coroutine vs thread - 성능 테스트...

fun main() = runBlocking {

    val count = 10_000

    var time = measureTimeMillis {
        //thread 를 10000개 구동시켜서.. 각각의 업무 처리되게..
        val threadJob = List(count){
            thread {//thread 확장 함수..
                Thread.sleep(1000)
                print(".")
            }
        }
        threadJob.forEach { it.join() }//모든 스레드 끝날 때까지.. 대기..
    }
    println()
    println("thread $count, total time : $time")

    //동일 업무를 코루틴으로.. 작성....
    time = measureTimeMillis {
        val coroutineJob = List(count){
            launch {
                delay(1000)
                print(".")
            }
        }
        coroutineJob.forEach { it.join() }
    }
    println()
    println("coroutine $count, total time : $time")

}
//thread 10000, total time : 3670
//coroutine 10000, total time : 1191