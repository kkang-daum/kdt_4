package com.example.ch1.section2

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking

//GlobalScope, CoroutineScope()

fun main() = runBlocking{
    //GlbalScope 에 기본 적용된 Dispatcher 는 Default
    GlobalScope.launch {
        repeat(3){
            println("step1... ${Thread.currentThread().name}")
        }
    }
    //step1... DefaultDispatcher-worker-1
    //step1... DefaultDispatcher-worker-1
    //step1... DefaultDispatcher-worker-1

    println()

    //GlobalScope 는 이미 생성된 scope 객체임으로 Scope 에 dispatcher 를 지정할 수 없지만..
    //GlobalScope 에서 실행되는 개별 코루틴에 선언 가능..
    GlobalScope.launch(newSingleThreadContext("myThread")) {
        repeat(3){
            println("step2... ${Thread.currentThread().name}")
        }
    }
    //step2... myThread
    //step2... myThread
    //step2... myThread

    println()

    //코루틴 구조화...
    //- 동일 스코프내에서 여러 코루틴이 구조화..
    //코루틴 내에서 또 다른 코루틴 구동..
    val job1 = launch {
        launch {
            repeat(5){
                println("sub step3.. $it")
                delay(200)
            }
        }
        repeat(5){
            println("super step3.. $it")
            delay(200)
        }
    }
    delay(500)
    job1.cancel()
    //super step3.. 0
    //sub step3.. 0
    //super step3.. 1
    //sub step3.. 1
    //super step3.. 2
    //sub step3.. 2
    //==>코루틴이 구조화 되면 상위가 취소될때.. 그 하위 모든 코루틴이 취소된다.
    //scope 의 job 이 cancel 되면.. scope 의 모든 코루틴이 취소되며..
    //상위 코루틴이 취소되면.. 하위 코루틴도 취소된다..

    println()

    val job2 = launch {
        GlobalScope.launch {
            repeat(5){
                println("sub step4.. $it")
                delay(200)
            }
        }
        repeat(5){
            println("super step4.. $it")
            delay(200)
        }
    }
    delay(500)
    job2.cancel()
    //super step4.. 0
    //sub step4.. 0
    //super step4.. 1
    //sub step4.. 1
    //sub step4.. 2
    //super step4.. 2
    //sub step4.. 3
    //sub step4.. 4
    //==>GlobalScope 의 코루틴은 구조화 개념이 없다.. 상위 코루틴 종료되도 종료되지 않는다..

    delay(5000)

    //GlobalScope 는 스코프의 job 개념이 없다..
    //GlobalScope 의 개별 코루틴들이 job 을 가지고 개별 제어가 되는 것이지..
    //scope 전체 제어 개념이 없다..
    //CoroutineScope() 는 job 가지며, context 지정 가능..
    val job3 = Job()
    val scope1 = CoroutineScope(Dispatchers.IO + job3)
    scope1.launch {
        repeat(5){
            println("step5... $it")
            delay(200)
        }
    }
    scope1.launch {
        repeat(5){
            println("step6... $it")
            delay(200)
        }
    }
    delay(300)
    job3.cancel()
    //step5... 0
    //step6... 0
    //step5... 1
    //step6... 1

    delay(2000)
}