package org.example.ch4.section3_inline

fun myFun1(argFun: (Int) -> Int): Int {
    return argFun(10)
}

inline fun myFun2(argFun: (Int) -> Int): Int {
    return argFun(10)
}

fun testFun(){
    myFun1 { it * 10 }
    myFun2 { it * 20 }
}

//local return vs non local return.................
fun some1(fun1: (Int, Int) -> Int, fun2: (Int) -> Int): Int {
    val result1 = fun1(10, 20)
    val result2 = fun2(result1)
    println("some1....")
    return result2
}

inline fun some2(fun1: (Int, Int) -> Int, fun2: (Int) -> Int): Int {
    val result1 = fun1(10, 20)
    val result2 = fun2(result1)
    println("some2....")
    return result2
}

//localReturn, 나 자신을 리턴시켜라... 람다 자체가 종료되라..
//localReturn, inline 함수에 대입되는 람다함수, inline 이 아닌 함수에 대입되는 람다함수
//모두 사용 가능..
fun localResturnTestFun(){
    var result = some1({ arg1, arg2 -> arg1 * arg2}){ result ->
        if(result < 0) return@some1 0
        else return@some1 result
    }
    println("test1... $result")
    //some1....
    //test1... 200

    result = some2({ arg1, arg2 -> arg1 * arg2}){ result ->
        if(result < 0) return@some2 0
        else return@some2 result
    }
    println("test2... $result")
    //some2....
    //test2... 200

}

fun nonLocalReturnTest(): Int {
    //non local return, 일반 hof 에서 사용 불가
//    some1({arg1, arg2 -> arg1 * arg2}){ result ->
//        if(result < 0) return 0
//        else return result
//    }//error...

    //inline hof 에서는 non local return 된다..
    some2({arg1, arg2 -> arg1 * arg2}){ result ->
        if(result < 0) return 0
        else return result
    }//ok...
    return 0
}

fun main() {
    testFun()
    localResturnTestFun()
    nonLocalReturnTest()
    //test2... 200
}