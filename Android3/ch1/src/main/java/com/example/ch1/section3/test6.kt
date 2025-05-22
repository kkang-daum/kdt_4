package com.example.ch1.section3

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//CompletableDeferred 활용....

//외부에서 이 함수를 호출해서 결과를 받아야 하는데..
//결과가 나오기까지.. 시간이 오래 걸린다..
//호출한 곳을 대기시키지 않고 싶다..
fun something(): Deferred<String> {
    val deferred = CompletableDeferred<String>()

    GlobalScope.launch {
        delay(500)
        deferred.complete("hello world")
    }

    return deferred
}

fun main() = runBlocking {
    println("step1...")
    val deferred = something()
    println("step2...")
    println("result : ${deferred.await()}")
    //step1...
    //step2...
    //result : hello world

    //test2.................. deferred 에 의한 결과를 callback 받고 싶다...
    val deferred1 = CompletableDeferred<String>()
    //누가.. 결과를 양산할지 모르겠는데.. 나는 결과가 만들어지면.. 움직이고 싶다..
    deferred1.invokeOnCompletion { e ->
        if(e == null){
            //정상 데이터가 있는 경우.. 에러가 발생하지 않은 경우..
            println("callback... ${deferred1.getCompleted()}")
        }
    }
    
    launch {
        delay(1000)
        deferred1.complete("hello....")
    }

    println()
}