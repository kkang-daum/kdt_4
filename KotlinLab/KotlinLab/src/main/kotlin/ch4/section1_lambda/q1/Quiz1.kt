package org.example.ch4.section2_lambda

class FunctionTest {
    val method1: () -> Any = { println("method1 call") }
    val method2: () -> String = { "Hello World method2" }
    val method3: (Int) -> Int = {
        var sum = 0
        for (i in 1..it) { sum += i }
        sum
    }
    val method4: (Int, Int) -> Int = {no1, no2 ->
        no1 + no2
    }
    val method5: (Int, Int) -> Int? = {no1, no2 ->
        if (no2 == 0) null
        else no1/no2
    }
}