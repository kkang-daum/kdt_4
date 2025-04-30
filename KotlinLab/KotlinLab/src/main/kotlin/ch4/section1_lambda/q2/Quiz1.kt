package ch4.section2_lambda

class FunctionTest {
    val method1 = run { println("method1") }
    val method2: () -> String = { "Hello World method2" }
    val method3: (Int) -> Int = { no ->
        var sum = 0
        for (i in 1..no) sum += i
        sum
    }
    val method4: (Int, Int) -> Int = { no1, no2 -> no1 + no2 }
    val method5: (Int, Int) -> Int? = { no1, no2 ->
        if (no2 == 0) null
        else no1 / no2
    }
}

fun main() {
    val test = FunctionTest()
    test.method1
    println(test.method2())
    println(test.method3(5))
    println(test.method4(2, 1))
    println(test.method5(2, 1))
}