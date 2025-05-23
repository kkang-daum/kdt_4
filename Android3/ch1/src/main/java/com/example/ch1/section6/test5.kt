package com.example.ch1.section6

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//stateflow vs sharedflow.......

fun main() = runBlocking {
    //MutableStateFlow.....
    val stateFlow = MutableStateFlow(0)
    val stateFlowJob = launch {
        stateFlow.collect {
            println("state flow... $it")
        }
    }
    delay(100)
    stateFlow.value = 10
    delay(100)
    stateFlow.value = 10
    delay(100)
    stateFlow.value = 10
    delay(100)
    stateFlow.value = 20
    delay(100)
    stateFlowJob.cancel()

    println()

    //MutableSharedFlow......................
    val sharedFlow = MutableSharedFlow<Int>()
    val sharedFlowJob = launch {
        sharedFlow.collect {
            println("shared flow... $it")
        }
    }
    delay(100)
    sharedFlow.emit(10)
    delay(100)
    sharedFlow.emit(10)
    delay(100)
    sharedFlow.emit(10)
    delay(100)
    sharedFlow.emit(20)
    delay(100)
    sharedFlowJob.cancel()

}
//동일한 값으로.. 발행은 안된다.. 상태 표현이 목적임으로..
//state flow... 0
//state flow... 10
//state flow... 20
//
//동일한 값도 발행이 된다.. 몇개의 데이터 누적.. 이벤트 발생에 의미가 있음으로..
//shared flow... 10
//shared flow... 10
//shared flow... 10
//shared flow... 20