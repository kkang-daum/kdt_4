package com.example.ch1.section8

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking

//flowOn vs stateIn

fun main() = runBlocking{
    //flowOn............ intermediator operator... context 교체...
    val flow1 = flow<Int> {
        println("flow1... ${Thread.currentThread().name}")
        repeat(3){ emit(it) }
    }.flowOn(Dispatchers.IO)

    delay(200)
    println("step1")
    flow1.collect {
        println("collect $it on ${Thread.currentThread().name}")
    }
    //flowOn() 은 terminal operator 가 아니다.. 그 자체로 flow 를 실행시킬 수 없다..
    //단지.. flow 의 context 정보 교체가 목적이다...
    //step1
    //flow1... DefaultDispatcher-worker-1
    //collect 0 on main
    //collect 1 on main
    //collect 2 on main

    println()

    //stateIn()................................
    val scope = CoroutineScope(Dispatchers.Default + Job())
    val flow2 = flow {
        println("flow 2 : ${Thread.currentThread().name}")
        repeat(3) { emit(it) }
    }//여기까지는.. cold stream 이다..
        .stateIn(//terminal operator... 지정된 scope 를 이용한 coroutine 을 만들어 flow 실행.
            //cold stream 으로 선언된 flow 를 hot stream 으로 변형..
            scope = scope,
            started = SharingStarted.Eagerly,
            initialValue = 0
        )
    delay(200)
    println("step2..")
    flow2.take(3).collect {
        println("flow2 collect.. $it")
    }
    scope.cancel()
    //==>collect 되기 전에.. flow 가 먼저 움직였다..
    //flow 2 : DefaultDispatcher-worker-1
    //step2..
    //flow2 collect.. 2
}