package com.example.ch1.section8.test8

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.runBlocking

fun something() = flow {
    repeat(3){
        delay(100)
        emit(it)
        println("emit $it")
    }
}

fun main() = runBlocking {
    val list = listOf(1, 3, -2, 10, 12, -5)

    list.asFlow()
        .transform {
            if(it > 0){
                if(it % 2 == 0){
                    emit("Even Number $it")
                }else {
                    emit("Odd Number $it")
                }
            }
        }
        .collect {
            println(it)
        }
    //Odd Number 1
    //Odd Number 3
    //Even Number 10
    //Even Number 12

    println()

    something()
        .buffer(5)
        .conflate()
        .collect {
            println("collect... $it")
            delay(300)
        }
}
//collect 만 사용한 경우....
//collect... 0
//emit 0
//collect... 1
//emit 1
//collect... 2
//emit 2

//buffer().......
//emit 0
//collect... 0
//emit 1
//emit 2
//collect... 1
//collect... 2

//conflate() 을 추가한 경우.............
//==>1번 발행으로.. 구독자가 움직이지는 않았다.. 구독자가 구독하려고 하는 순간의 항상 최신 데이터로만
//emit 0
//collect... 0
//emit 1
//emit 2
//collect... 2