package com.example.ch1.section1

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//이 함수가 코루틴에 의해 실행되어 비동기 업무 처리를 목적으로 하는 함수라고 하면..
//일반 함수로 만들어도 되지만.. suspend 함수로 만들게 되면 thread 사용에 더욱 효율적일 수 있다.

//suspend 함수는.. 함수 실행이 일시 중단(suspension) 될 가능성이 있는 함수..
//delay 가 있거나.. IO 가 있거나.. 대표적인 suspension 상황.. 이 발생될 수 잇고..
//그러면.. 이 함수를 실행시킨 thread 를 non-blocking 으로 실행시켜 준다..
suspend fun suspendFun(no: Int): Int {
    var sum = 0
    for(i in 1..no){
        delay(i * 10L)
        sum += i
    }
    return sum
}

fun noSuspendFun(no: Int): Int {
//    suspendFun(10)//error.. suspend 함수는 꼭 suspend 함수 내에서만 호출이 가능하다..
    return 10
}

fun main() = runBlocking {
    for(i in 1..3){
        launch(Dispatchers.Default) {
            println("coroutine.. .$i start : ${Thread.currentThread().name}")
//            noSuspendFun(10)
            //test2.............
            suspendFun(10)
            println("coroutine.. .$i end : ${Thread.currentThread().name}")
        }
    }
    Thread.sleep(2000)
}
//noSuspendFun(10).......... 일반 함수 호출한 경우의 결과................
//함수를 실행시킨 스레드와 함수를 끝낸 스레드가 동일하다..
//coroutine.. .1 start : DefaultDispatcher-worker-1
//coroutine.. .1 end : DefaultDispatcher-worker-1
//coroutine.. .2 start : DefaultDispatcher-worker-1
//coroutine.. .3 start : DefaultDispatcher-worker-2
//coroutine.. .3 end : DefaultDispatcher-worker-2
//coroutine.. .2 end : DefaultDispatcher-worker-1


//suspendFun(10)............... 일시중단 함수를 호출한 경우의 결과..............
//coroutine.. .1 start : DefaultDispatcher-worker-1
//coroutine.. .2 start : DefaultDispatcher-worker-2
//coroutine.. .3 start : DefaultDispatcher-worker-3
//coroutine.. .1 end : DefaultDispatcher-worker-3
//coroutine.. .2 end : DefaultDispatcher-worker-2
//coroutine.. .3 end : DefaultDispatcher-worker-3