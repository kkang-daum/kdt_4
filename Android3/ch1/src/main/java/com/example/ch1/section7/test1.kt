package com.example.ch1.section7

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking

fun something(): Flow<Int> = flow {
    repeat(3){
        emit(it)
        println("emit...$it")
        delay(100)
    }
}

fun main() = runBlocking {
    something().collectIndexed { index, value ->
        println("index: $index, value : $value")
    }
    //index: 0, value : 0
    //emit...0
    //index: 1, value : 1
    //emit...1
    //index: 2, value : 2
    //emit...2

    println()

    something().collectLatest {
        println("collectLatest 1 : $it")
        delay(150)
        println("collectLatest 2: $it")
    }
    //데이터 발행은 3번 되었고.. 그데이터를 이용하기 위한 1번 위치는 3번 찍혔다..
    //하지만.. 데이터 수신후 업무가 완료되어 찍히는 2번은 3번 찍히지 않았다..
    //==>데이터 수신후 업무 진행 도중에 새로운 데이터가 발생이 되면.. 현재 업무처리가 의미가 없어지는
    //경우.. 새로운 데이터로 다시 업무가 진행되어야 하는 경우...
    //collectLatest 1 : 0
    //emit...0
    //collectLatest 1 : 1
    //emit...1
    //collectLatest 1 : 2
    //emit...2
    //collectLatest 2: 2

    println()
    something().toList().forEach { println("list : $it")}
    //발행시마다.. 움직이지는 않았다.. 모든 발행이 완료된다음.. 한꺼번에 처리했다..
    //emit...0
    //emit...1
    //emit...2
    //list : 0
    //list : 1
    //list : 2
}