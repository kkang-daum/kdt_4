package com.example.ch1.section8

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
   flow {
       println("Flow : ${Thread.currentThread().name}")
       repeat(3){
           delay(100)
           emit(it)
           println("emit $it : ${Thread.currentThread().name}")
       }
   }
       .map {
           println("map first $it : ${Thread.currentThread().name}")
           it * 2
       }
       .flowOn(Dispatchers.IO)//inermediator... 윗부분에 영향...
       .map {
           println("map second $it : ${Thread.currentThread().name}")
           it * 2
       }
       .collect {
           println("collect $it : ${Thread.currentThread().name}")
       }
}

//Flow : DefaultDispatcher-worker-1
//map first 0 : DefaultDispatcher-worker-1
//emit 0 : DefaultDispatcher-worker-1
//map second 0 : main
//collect 0 : main
//map first 1 : DefaultDispatcher-worker-1
//emit 1 : DefaultDispatcher-worker-1
//map second 2 : main
//collect 4 : main
//map first 2 : DefaultDispatcher-worker-1
//emit 2 : DefaultDispatcher-worker-1
//map second 4 : main
//collect 8 : main