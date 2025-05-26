package com.example.ch1.section8

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

//stateIn, sharedIn 의 WhileSubscribed....

fun something() = flow<Int> {
    repeat(10){
        delay(100)
        println("emit $it")
        emit(it)
    }
}.shareIn(//hot stream 에 의해 flow 동작하게...
    CoroutineScope(Dispatchers.IO),//지정한 scope 내에서 동작하는 코루틴을 만들어 flow 실행..
    //구독자가 있는 경우에만 동작한다.. 구독자가 모두 사라지면 어떻게 움직일 것인가...
    SharingStarted.WhileSubscribed(stopTimeoutMillis = 200)
)

fun main() = runBlocking {
    val flow = something()
    delay(200)
    launch {
        withTimeout(300){
            flow.collect { println("job1 receive.. $it")}
        }
    }
    delay(1000)
}
//stopTimeoutMillis = 0....................
//==>구독자가 나타나야.. flow 데이터 발행했다.
//==>구독자가 사라지면.. 바로 멈추었다..
//emit 0
//job1 receive.. 0
//emit 1
//job1 receive.. 1

//stopTimeoutMillis = 200...................
//==>구독자가 나타나야.. 움직인다..
//==>구독자가 사라지더라도.. 200 동안은 움직인다..
//emit 0
//job1 receive.. 0
//emit 1
//job1 receive.. 1
//emit 2
//emit 3