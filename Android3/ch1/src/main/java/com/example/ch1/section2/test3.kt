package com.example.ch1.section2

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newFixedThreadPoolContext
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking

fun main() = runBlocking{
    //main start.... main
    println("main start.... ${Thread.currentThread().name}")

    val job = launch {
        //코루틴을 구동시키면서 특별한 dispatcher 를 지정하지 않으면..
        //그 코루틴을 구동시켰던 thread 에 의해 동작하게 된다..
        //coroutine start.... main
        println("coroutine start.... ${Thread.currentThread().name}")
        delay(300)
    }
    job.join()

    listOf("one", "two", "three").forEachIndexed { index, value ->
        launch(newSingleThreadContext("myThread $value")) {
            println("coroutine $value start.... ${Thread.currentThread().name}")
            delay(100L + index * 100L)//중단함수를 만났다..
            println("coroutine $value end.... ${Thread.currentThread().name}")
        }
    }
    //==> 중단함수를 만났지만.. 스레드가 교체되지 않는다..newSingleThreadContext 을 지정하면..
    //coroutine two start.... myThread two
    //coroutine one start.... myThread one
    //coroutine three start.... myThread three
    //coroutine one end.... myThread one
    //coroutine two end.... myThread two
    //coroutine three end.... myThread three

    delay(3000)

    println()

    //스레드 풀 내의 스레드만 이용해서.. 동작..
    //풀 내에서 스레드 교체는 가능하고...
    listOf("one", "two", "three").forEachIndexed { index, value ->
        launch(newFixedThreadPoolContext(2, "myThreadPool")) {
            println("coroutine $value start.... ${Thread.currentThread().name}")
            delay(100L + index * 100L)//중단함수를 만났다..
            println("coroutine $value end.... ${Thread.currentThread().name}")
        }
    }
    //coroutine three start.... myThreadPool-1
    //coroutine one start.... myThreadPool-1
    //coroutine two start.... myThreadPool-1
    //coroutine one end.... myThreadPool-1
    //coroutine two end.... myThreadPool-1
    //coroutine three end.... myThreadPool-1

    delay(3000)


}