package com.example.ch1.section5

import kotlinx.coroutines.channels.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//fan out...........

fun main() = runBlocking{
    val myChannel1 = produce<Int>{
        var x = 1
        while(true){
            delay(100)
            send(x++)
        }
    }

    //하나의 채널의 데이터를 여러 구독자가.. 받는다..
    launch {
        myChannel1.consumeEach {
            println("job1... receive : $it")
        }
    }
    launch {
        myChannel1.consumeEach {
            println("job2... receive : $it")
        }
    }
    launch {
        myChannel1.consumeEach {
            println("job3... receive : $it")
        }
    }
    delay(3000)
    myChannel1.cancel()
}
//job1... receive : 1
//job2... receive : 2
//job3... receive : 3
//job1... receive : 4
//job2... receive : 5
//job3... receive : 6

//fan out... 1:N 가능하다...
//하나의 데이터를 여러 구독자가 같이 받을 수는 없다..
//구독의 순서를 보장할 수는 없다..
//==>1:1, 1:n, n:1, n:m 모두 가능하기는 하지만..
//주로.. 1:1 에 이용...