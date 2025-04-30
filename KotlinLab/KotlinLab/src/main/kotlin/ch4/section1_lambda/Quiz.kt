package org.example.ch4.section1_lambda

class FunctionTest {
//    val method1: () -> Unit = { println("....")}
    val method1 = { println("....") }

    val method2 = { "hello world method2" }

//    val method3: (Int) -> Int = { no ->
//        var sum = 0
//        for(i in 1..no){
//            sum += i
//        }
//        sum
//    }

//    val method3= { no: Int ->
//        var sum = 0
//        for(i in 1..no){
//            sum += i
//        }
//        sum
//    }

    val method3: (Int) -> Int = {
        var sum = 0
        for(i in 1..it){
            sum += i
        }
        sum
    }

    val method4 = {no1: Int, no2: Int ->
        no1 + no2
    }
    val method5 = {no1: Int, no2: Int ->
        if(no2==0)
            null
        else
            no1/no2
    }
}