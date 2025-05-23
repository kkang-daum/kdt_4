package com.example.ch1.section6

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//MutableSharedFlow... hot stream... 새로운 구독자가.. 현재 단일 값 뿐만이 아니라..
//이전에 발행했던 값도 같이 받을 수 있는 경우..
//replay = 2 : 새로운 구독자가.. 과거에 발행했던 몇개의 데이터를 받을 수 있는지에 대한 설정..
//초기 값은 없다..

val sharedFlow = MutableSharedFlow<Int>(replay = 2)

suspend fun changeData(data: Int){
    println("emit... $data")
    sharedFlow.emit(data)
}

fun main() = runBlocking{
    //데이터 발행 5번 해보고...
    launch {
        repeat(5){
            delay(100)
            changeData(it)
        }
    }
    delay(500)

    val job1 = launch {
        println("job1.. collect start...")
        sharedFlow.collect {
            println("job1 $it")
        }
    }
    delay(1000)
    job1.cancel()
}
//구독자가 나타나지 않아도 움직였다.. ==> hot stream...
//최신 발행(3) 이전에 발행했던 2까지 구독되었다..(replay = 2) 때문에..
//emit... 0
//emit... 1
//emit... 2
//emit... 3
//job1.. collect start...
//job1 2
//job1 3
//emit... 4
//job1 4