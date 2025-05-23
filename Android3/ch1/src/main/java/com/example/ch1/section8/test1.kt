package com.example.ch1.section8

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

//flow context preservation....................

fun simple(): Flow<Int> = flow {
    println("flow start.. .${Thread.currentThread().name}")
    repeat(3){
        emit(it)
        println("emit.. .${Thread.currentThread().name}")
    }
}

fun main() = runBlocking{
    println("main context : ${Thread.currentThread().name}")
    simple()
        .collect {
            println("collect : $it, ${Thread.currentThread().name}")
        }
    //main context : main
    //flow start.. .main
    //collect : 0, main
    //emit.. .main
    //collect : 1, main
    //emit.. .main
    //collect : 2, main
    //emit.. .main
    //==>flow 와 collect 부분 모두 collect 를 실행시켰던 스레드(main) 에 의해 실행되었다..

    println()

    //다른 스레드에 의해 flow 가 실행되게 하고 싶다면..
    withContext(Dispatchers.IO){
        println("io context: ${Thread.currentThread().name}")
        simple()
            .collect {
                println("collect : $it, ${Thread.currentThread().name}")
            }
    }
    //context preservation 은.. collect 부분과 flow 부분이 동일 context 이면 된다..
    //io context: DefaultDispatcher-worker-1
    //flow start.. .DefaultDispatcher-worker-1
    //collect : 0, DefaultDispatcher-worker-1
    //emit.. .DefaultDispatcher-worker-1
    //collect : 1, DefaultDispatcher-worker-1
    //emit.. .DefaultDispatcher-worker-1
    //collect : 2, DefaultDispatcher-worker-1
    //emit.. .DefaultDispatcher-worker-1

    //error.... 상황....................
    flow {
        withContext(Dispatchers.IO){
            emit(1)
        }
    }.collect {
        println("collect $it")
    }
}