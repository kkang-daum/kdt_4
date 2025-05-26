package com.example.ch1.section8

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking

val flow1 = flow<Int>{
    for(i in 1..5){
        delay(100)
        println("flow1 emit $i")
        emit(i)
    }
}

val flow2 = flow<Int>{
    for(i in 11..13){
        delay(200)
        println("flow2 emit $i")
        emit(i)
    }
}

fun main() = runBlocking {
    flow1
        .combine(flow2){a, b ->
            "$a - $b"
        }
        .collect {
            println("collect $it")
        }
}

//zip...........모두 최신 데이터가 발행된 경우.. 데이터 발행...............
//flow1 emit 1
//flow2 emit 11
//collect 1 - 11
//flow1 emit 2
//flow2 emit 12
//collect 2 - 12
//flow1 emit 3
//flow2 emit 13
//collect 3 - 13

//combine.. 하나의 flow 만 데이터를 발행하면.. 최종 발행....
//flow1 emit 1
//flow2 emit 11
//collect 1 - 11
//flow1 emit 2
//collect 2 - 11
//flow1 emit 3
//collect 3 - 11
//flow2 emit 12
//collect 3 - 12
//flow1 emit 4
//collect 4 - 12
//flow1 emit 5
//collect 5 - 12
//flow2 emit 13
//collect 5 - 13