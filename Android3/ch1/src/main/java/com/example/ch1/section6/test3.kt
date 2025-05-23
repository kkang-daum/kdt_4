package com.example.ch1.section6

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//MutableStateFlow......................
//hot stream 이다.. 어떤 특정 상태(값)을 발행하고 그 값이 변경(상태변경) 시에..
//구독자들을 움직이게해서.. 업무처리가 되게 한다..

val stateFlow = MutableStateFlow(0)//상태를 표현함이 목적임으로 초기값 있어야 한다..

//개발자 업무 함수인데.. 이 함수 실행에 의해 상태가 발생하게 된다는 가정...
suspend fun changeState(data: Int){
    stateFlow.emit(data)
    //아래의 코드도 된다..
//    stateFlow.value = data
}

fun main() = runBlocking {
    changeState(1)
    changeState(2)
    //flow 는 만들어 졌다.. 아직.. 구독자는 없다.. 그래도 움직인다.. hotstream..
    println("............ ${stateFlow.value}")//2

    //구독자가 나타났다....
    //상태 목적이니 flow 의 현재 값이 관심대상이다..
    val job1 = launch {
        stateFlow.collect {
            println("job1.. $it")
        }
    }
    delay(100)
    changeState(3)
    delay(100)
    job1.cancel()
    //job1.. 2
    //job1.. 3
    //==>상태 값이 변경 될때마다.. 감지는 했다.. 하지만 1을 구독하지는 않았다.. 현재 상태에
    //관심이 있음으로.. 이전에 발행했던 상태값은 못 얻는다..


    //구독자가 모두 없어 졌다.. 하지만.. 나는 상태를 발행한다..
    changeState(4)
    changeState(5)

    //동시에 여러 구독자가 나타났다..
    val job2 = launch {
        stateFlow.collect {
            println("job2 $it")
        }
    }
    val job3 = launch {
        stateFlow.collect {
            println("job3 $it")
        }
    }
    delay(100)
    changeState(6)
    delay(100)
    job2.cancel()
    job3.cancel()
    //job2 5
    //job3 5
    //job2 6
    //job3 6
    //여러 구독자가 동시에 동일 값을 받게 된다..
}