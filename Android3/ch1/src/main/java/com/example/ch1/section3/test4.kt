package com.example.ch1.section3

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.whileSelect
import kotlinx.coroutines.withContext

//coroutine lifecycle... cancel......

fun main() = runBlocking{
    //test1... lifecycle test...........................
    val job1 = launch(start = CoroutineStart.LAZY) {
        println("job1...1")
        delay(500)
        println("job1...2")
    }
    println("main step1 : isActive : ${job1.isActive}, isCannelled : ${job1.isCancelled}" +
            ", isCompleted : ${job1.isCompleted}")
    delay(200)
    job1.start()
    println("main step2 : isActive : ${job1.isActive}, isCannelled : ${job1.isCancelled}" +
            ", isCompleted : ${job1.isCompleted}")
    job1.join()
    println("main step3 : isActive : ${job1.isActive}, isCannelled : ${job1.isCancelled}" +
            ", isCompleted : ${job1.isCompleted}")
    //main step1 : isActive : false, isCannelled : false, isCompleted : false
    //main step2 : isActive : true, isCannelled : false, isCompleted : false
    //job1...1
    //job1...2
    //main step3 : isActive : false, isCannelled : false, isCompleted : true


    //test2... cancel... coroutine api 사용하지 않는 경우 ==> cancel 이 요청되어도..자동 cancel 되지 않는다..
//    println()
//    val job2 = launch(Dispatchers.IO) {
//        var start = System.currentTimeMillis()
//        var i = 0
//        while(i < 5){
//            if(System.currentTimeMillis() >= start){
//                i++
//                println("job2... $i")
//                start += 500
//            }
//        }
//    }
//    delay(1000)
//    println("main cancel......")
//    job2.cancelAndJoin()
//    println("main cancel.. end...")
    //job2... 1
    //job2... 2
    //job2... 3
    //main cancel......
    //job2... 4
    //job2... 5
    //main cancel.. end...


    //test3.... cancel 된 것인지 체크해서.. exception 발생시킨다... ==> 정상 취소된다..
//    println()
//    val job2 = launch(Dispatchers.IO) {
//        var start = System.currentTimeMillis()
//        var i = 0
//        while(i < 5){
//            if (isActive) {
//                if (System.currentTimeMillis() >= start) {
//                    i++
//                    println("job2... $i")
//                    start += 500
//                }
//            }else {
//                throw CancellationException()
//            }
//        }
//    }
//    delay(1000)
//    println("main cancel......")
//    job2.cancelAndJoin()
//    println("main cancel.. end...")
    //job2... 1
    //job2... 2
    //job2... 3
    //main cancel......
    //main cancel.. end...


    //test4... cancel 에 의해 exception 발생시켜서.. 종료되기는 하지만..마지막 처리할 내용이 있다..
    //finally 혹은 catch 에서.. cancel 에 의해 exception 이 발생되는 구문(delay)을 사용한다면..
    //==>의도했던.. catch, finally 부분이 정상 실행되지 않을 수도 있다..
//    println()
//    val job2 = launch(Dispatchers.IO) {
//        try {
//            var start = System.currentTimeMillis()
//            var i = 0
//            while (i < 5) {
//                if (isActive) {
//                    if (System.currentTimeMillis() >= start) {
//                        i++
//                        println("job2... $i")
//                        start += 500
//                    }
//                } else {
//                    throw CancellationException()
//                }
//            }
//        }finally {
//            repeat(3){
//                println("coroutine finally.. $it")
//                delay(100)
//            }
//        }
//    }
//    delay(1000)
//    println("main cancel......")
//    job2.cancelAndJoin()
//    println("main cancel.. end...")
    //job2... 1
    //job2... 2
    //job2... 3
    //main cancel......
    //coroutine finally.. 0
    //main cancel.. end...


    //test5..... catch, finally 정상 실행시키고.. 종료시키기...
    println()
    val job2 = launch(Dispatchers.IO) {
        try {
            var start = System.currentTimeMillis()
            var i = 0
            while (i < 5) {
                if (isActive) {
                    if (System.currentTimeMillis() >= start) {
                        i++
                        println("job2... $i")
                        start += 500
                    }
                } else {
                    throw CancellationException()
                }
            }
        }finally {
            //NonCancellable 이 지정된 영역은 그 영역에서 exception 이 발생되어도 취소되지 않고 끝까지 실행.
            withContext(NonCancellable) {
                repeat(3) {
                    println("coroutine finally.. $it")
                    delay(100)
                }
            }
        }
    }
    delay(1000)
    println("main cancel......")
    job2.cancelAndJoin()
    println("main cancel.. end...")
    //job2... 1
    //job2... 2
    //job2... 3
    //main cancel......
    //coroutine finally.. 0
    //coroutine finally.. 1
    //coroutine finally.. 2
    //main cancel.. end...

}