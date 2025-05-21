package com.example.ch1.section1

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//코루틴은 비동기를 목적으로 한다...

//runBlocking 은 main 함수 혹은 테스트 함수에서 coroutine api 를 사용할 목적으로 설계..
fun main() = runBlocking{
    //main thread 에 의해 실행 시작...
    println("step1.....")

    //코루틴을 실행...
    //비동기를 목적으로 한다.. 코루틴을 실행시킨 스레드(main thread) 를 멈추게(suspension) 하지 않는다.
    //launch ... coroutine builder... 코루틴을 하나 만들고.. 실행..
    //리턴 값은 코루틴에 의해 실행되는 업무를 지칭.. job 으로.. 여러가지 제어..
    val job = launch {
        var sum = 0
        for(i in 1..10){
            delay(100)
            sum += i
        }
        println("coroutine.... $sum")
    }
    println("step2.....")
    job.join()//job 으로 표현되는 코루틴이 끝날 때까지 대기..
    //main() 함수에서 테스트 했고.. main() 함수가 끝나면 process 가 종료되어..
    //테스트를 위해서 쓴거다..
    println("main end..")
}

//==>코루틴을 구동 시켰던 main thread 가 coroutine 이 끝날 때까지 대기하지 않았다.. 같이 움직였다.
//비동기로 코루틴이 실행되었다..
//step1.....
//step2.....
//coroutine.... 55
//main end..