package com.example.ch1.section3

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//starter test.....................

fun main() = runBlocking{
    //start... 지정하지 않은 경우.. default 가 지정된 경우..
    //만들자마자 구동.. 구동되기 전에 취소하면 실행 안된다..
    val job1 = launch {
        println("job1.....")
    }
    job1.cancel()

    val job2 = launch(start = CoroutineStart.ATOMIC) {
        println("job2..1")
        delay(200)
        println("job2..2")
    }
    job2.cancel()
    //job2..1
    //==>CoroutineStart.ATOMIC 을 지정하면.. 만들자마자 구동. 구동전에 취소해도 구동..
    //구동하다가 취소될 수는 있고..

    delay(200)

    val job3 = launch(start = CoroutineStart.UNDISPATCHED, context = Dispatchers.Default) {
        println("job3..1.. ${Thread.currentThread().name}")
        delay(200)
        println("job3..2.. ${Thread.currentThread().name}")
    }
    job3.join()
    //job3..1.. main
    //job3..2.. DefaultDispatcher-worker-1
    //==>CoroutineStart.UNDISPATCHED ..
    //코루틴의 시작은.. 현재 코루틴을 만들었던.. 스레드에 의해 시작(main)
    //중단함수를 만난 후 다시 재게될때.. context 에 지정된 스레드에 의해 실행되라..


    val job4 = launch(start = CoroutineStart.LAZY) {
        println("job4....")
    }
    delay(200)
    println("main 1")
    job4.start()
    job4.join()
    //main 1
    //job4....


}