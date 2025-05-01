package org.example.ch4.section2_hof

import org.example.ch3.section7_enum.test

fun main() {
    //hof 선언....
    fun hofFun(x1: Int, argFun: (Int) -> Int){
        val result = argFun(20)
        println("$x1, $result")
    }
    hofFun(10, { x: Int -> x * x })

    //마지막 매개변수가 함수 타입인 경우.. 그 매개변수에 전달하는 람다함수의 { } 을 ( ) 밖에
    hofFun(10){ x -> x + x }

    fun hofFun2(no: Int, argFun1: (Int) -> Int, argFun2: (Int) -> Boolean){
        argFun1(10)
        argFun2(10)
    }
    hofFun2(10, { it * it }, { it > 100 })
    hofFun2(10, { it * it }){ it > 100 }
//    hofFun2(10){it * it}{it > 100}//error...마지막 매개변수 하나에 한해서만 () 밖에..

    val array = arrayOf(10, 20, 15, 22, 8)//Array
    array.filter { it > 10 }
        .forEach { println(it) }

    //함수 리턴....
    fun hofFun3(str: String): (x1: Int, x2: Int) -> Int {
        when(str){
            "-" -> return {x1, x2 -> x1 - x2 }
            "+" -> return {x1, x2 -> x1 + x2 }
            "*" -> return {x1, x2 -> x1 * x2 }
            else -> return {x1, x2 -> x1 / x2}
        }
    }
    val resultFun = hofFun3("*")
    println(resultFun(10, 20))

    //데이터를 필터링 하는 함수를 만들고 싶다..
    //필터링 알고리즘을 함수 개발자가 결정을 해버리면 그 필터링 함수는 그 알고리즘대로만 필터링이 된다.
    //필터링은 내가 한다.. 필터링 알고리즘(함수)는 내가 결정하지 않는다. 외부에서 전달 받는데..
    fun myFilter(list: List<Int>, arg: (Int) -> Boolean): List<Int>{
        val resultList = mutableListOf<Int>()
        val iterator = list.iterator()
        while(iterator.hasNext()){
            val no = iterator.next()
            val result = arg(no)
            if(result){
                resultList.add(no)
            }
        }
        return resultList
    }

    val testList = listOf(10, 20, 3, 6, 30)
    myFilter(testList){ it > 15 }.forEach { println(it) }

}