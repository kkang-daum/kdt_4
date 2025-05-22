package com.example.ch1.section3

import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//Job vs CompletableJob

fun main() = runBlocking{
    //launch { } 가 리턴시킨 Job 이다.. 타입이 Job 이다..
    //==>코루틴 내부에서.. 완료 여부를 지정할때..
    //내부적으로 실행이 끝나거나.. exception 이 발생되거나.. 하면.. 완료되었다고 상태를 표현한다..
    val job1: Job = launch {
        repeat(2){
            delay(200)
            println("job1..$it")
        }
    }
    job1.join()//complete 상태가 될때까지.. 기다려라..
    println("main step1...")
    //job1..0
    //job1..1
    //main step1...
    //==>내부에서 complete 상태로 만들었기때문에..join() 밑줄이 실행된다..


    //코루틴 외부에서 job 을 선언해서 코루틴에 지정하는 경우...
    //외부에서 종료 여부를 결정하겠다..
    //내부가 끝났다고 하더라도.. 외부에서 명시적으로 종료 선언을 하지 않는한 complete 상태가 되지 않는다.
    val job2: CompletableJob = Job()

    launch(job2) {
        repeat(2){
            delay(200)
            println("job1..$it")
        }
    }
//    job2.join()//아래줄 실행 안된다..
    job2.cancel()
    println("main step2...")


    //complete().....................................
    val job3: CompletableJob = Job()

    launch(job3) {
        repeat(5){
            delay(200)
            println("job3..$it")
        }
    }
    delay(500)
    var isCompleted = job3.complete()//명시적으로 외부에서 complete 상태로 만든 경우이다..
    //cancel 효과가 난다..
    println("main step3... ${isCompleted}")

    delay(2000)
    //main step3... true
    //==>업무가 정상적으로 진행되었고.. 현재.. complete상태야..

    //이미 complete 상태의 job 으로 다른 업무 진행하려고 한 경우..
    launch(job3) {
        repeat(5){
            delay(200)
            println("job4..$it")
        }
    }
    delay(500)
    isCompleted = job3.complete()
    println("main step4... ${isCompleted}")//main step4... false
    //false 이미 종료된 job 이라는 의미..



}