package com.example.ch1.section2

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext

//CoroutineContext 재사용.......

class MyScope2: CoroutineScope {
    val superJob = Job()
    //여러곳에서 발생하는 에러를 한곳에서 일관되게 처리할 수 있다.. 큰 이점..
    val exceptionHandler = CoroutineExceptionHandler { context, exception ->
        println("coroutine exception.... ${context[CoroutineName.Key]} : $exception")
    }

    override val coroutineContext: CoroutineContext
        get() = CoroutineName("my-scope") + Dispatchers.Default + superJob + exceptionHandler
}

fun main() = runBlocking {
    val scope = MyScope2()
    scope.launch {
        println(
            "${coroutineContext[CoroutineName.Key]} is executing on thread : ${Thread.currentThread().name}")
        delay(300)
        throw Exception("error... coroutine1....")
    }
    scope.launch(CoroutineName("my-coroutine") + newSingleThreadContext("my one thread")){
        //상위(scope) 에서 정의한 context 정보를 상속받지만.. 원한다면 하위 coroutine 에서 교체 가능..
        println("${coroutineContext[CoroutineName.Key]} is executing on thread : ${Thread.currentThread().name}")
    }
    delay(1000)
}
//CoroutineName(my-scope) is executing on thread : DefaultDispatcher-worker-1
//CoroutineName(my-coroutine) is executing on thread : my one thread
//coroutine exception.... CoroutineName(my-scope) : java.lang.Exception: error... coroutine1....