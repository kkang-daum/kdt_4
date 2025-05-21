package com.example.ch1.section2

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext

//scope 를 만들어 코루틴들을 구조화하는 방법...

//custom scope 선언..
//CoroutineScope 인터페이스를 구현한 클래스의 객체가 코루틴 스코프가 된다.
class MyScope: CoroutineScope {
    val scopeJob: Job = Job()

    //이 scope 안에서 동작하는 모든 코루틴을 위한 공통 정보 설정..
    //Dispatchers.Default : 이 스코프의 코루틴들은 Dispatchers.Default 스레드 풀에서 동작한다..
    //scopeJob : 스코프 잡을 선언.. 이렇게 되면 코루틴들의 잡이 이 job 의 하위 job 이 된다..
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + scopeJob
}

fun main() = runBlocking {
    //스코프 하나 선언...
    val myScope = MyScope()
    val job1 = myScope.launch {
        repeat(3){
            delay(300)
            println("first coroutine $it")
        }
    }
    val job2 = myScope.launch {
        repeat(3){
            delay(200)
            println("second coroutine $it")
        }
    }
    delay(500)

    //개별 코푸틴 취소..
//    job1.cancel()
//    job2.cancel()

    //scope 에서 동작하는 모든 코루틴 취소...
    myScope.scopeJob.cancel()

    delay(500)
}
//job1.cancel()......job1 만 취소......................
//second coroutine 0
//first coroutine 0
//second coroutine 1
//second coroutine 2

//job2.cancel()..... job2만 취소.............
//second coroutine 0
//first coroutine 0
//second coroutine 1
//first coroutine 1
//first coroutine 2

//myScope.scopeJob.cancel()....... 스코프내의 모든 코루틴 취소..
//second coroutine 0
//first coroutine 0
//second coroutine 1

