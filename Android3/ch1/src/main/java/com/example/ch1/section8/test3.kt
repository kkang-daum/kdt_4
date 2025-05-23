package com.example.ch1.section8

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

//데이터베이스 + 네트워크 + UI
//데이터베이스에서 데이터 획득 => 연산.. => 네트워킹 => 화면 업데이트..

suspend fun database(id: Int): String {
    println("database... $id, ${Thread.currentThread().name}")
    delay(200)
    return "DB Data $id"
}

suspend fun network(id: Int): String {
    println("network... $id, ${Thread.currentThread().name}")
    delay(200)
    return "network $id"
}

fun updateUI(){
    println("update ui, ${Thread.currentThread().name}")
}

fun main() = runBlocking{
    flow<Int> {
        repeat(3){
            emit(it)
        }
    }
        .map {
            database(it)
        }
        .flowOn(Dispatchers.IO)
        .map {
            println("data processing.., ${Thread.currentThread().name}")
            //한참의 연산..
        }
        .flowOn(Dispatchers.Default)
        .map {
            network(0)
        }
        .flowOn(Dispatchers.IO)
        .collect {
            updateUI()
        }
}

