package com.example.ch1.section7.test2

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun something(): Flow<Int> = flow {
    repeat(3){
        emit(it)
        println("emit...$it")
        delay(100)
    }
}

fun main() = runBlocking {
    val flow = something()
    println("step1")
    launch {
        flow.collect { println("test1... $it") }
    }
    println("step2")

    //전체 코드가.. 코루틴에 의해서 동작하게 된다..
    //맨 마지막에.. launchIn() 지정하고.. 그 위에 다양한 intermediaotr 등록해서..
    //발행 데이터가 핸들링되게..
    flow.onEach { println("test2... $it") }//forEach 에 대당되는 flow 의 intermediator..
        .launchIn(this)//매개변수에 특별한 스코프를 지정해도 되고..
}