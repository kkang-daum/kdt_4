package com.example.ch1.section6

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

//flow builder..................

fun main() = runBlocking {
    //데이터 몇개가 있는데.(Collection 타입으로 묶이지 않은 상태)이걸 flow 로 발행하고 싶을 때..
    flowOf(1, 2, "hello")
        .collect {
            println("flowOf : $it")
        }

    listOf(1,2).asFlow()
        .collect { "asFlow : $it" }

}