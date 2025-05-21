package com.example.ch1.section1

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//코루틴을 실행시키는 thread pool...................
fun main() = runBlocking{
    listOf("one", "two", "three").forEachIndexed { index, value ->
        //코루틴을 구동시키면서.. 어느 thread pool에서 구동될지를 지정할 수 있다.
        launch(Dispatchers.Default) {
            //이 부분을 실행시켰던 thread....
            println("coroutine... $value start : ${Thread.currentThread().name}")
            //업무가 중단되는.. 즉 쉬고 있는 상황이 발생..

            //test1.................
//            Thread.sleep(100L + index * 100L)

            //test2....................
            delay(100L + index * 100L)
            println("coroutine... $value end : ${Thread.currentThread().name}")
        }
    }
    Thread.sleep(2000)
}

//Thread sleep 으로.. 업무가 대기상태가 된 경우.............
//==>각 코루틴을 실행시킨 스레드에 의해 업무 대기상태 후.. 그 스레드에서 끝났다..
//==>각 코루틴을 실행시킨 스레드가.. 유휴 상태에서 다른 업무를 처리하지 못하고 놀고 있었다..
//coroutine... one start : DefaultDispatcher-worker-1
//coroutine... two start : DefaultDispatcher-worker-2
//coroutine... three start : DefaultDispatcher-worker-3
//coroutine... one end : DefaultDispatcher-worker-1
//coroutine... two end : DefaultDispatcher-worker-2
//coroutine... three end : DefaultDispatcher-worker-3


//delay() 로 유휴 상태를 만든 경우.........................
//==>동시에 코루틴 3개를 구동시킨 것임으로.. 시작은 스레드 3개를 사용했다..
//유휴 상태 후에.. 각 코루틴 종료시.. 시작한 스레드에서 종료되지 않았다..
//종료시점에 동작하고 있는 스레드는 1개이다..
//==>더 적은 thread
//coroutine... one start : DefaultDispatcher-worker-1
//coroutine... two start : DefaultDispatcher-worker-2
//coroutine... three start : DefaultDispatcher-worker-3
//coroutine... one end : DefaultDispatcher-worker-1
//coroutine... two end : DefaultDispatcher-worker-1
//coroutine... three end : DefaultDispatcher-worker-1
