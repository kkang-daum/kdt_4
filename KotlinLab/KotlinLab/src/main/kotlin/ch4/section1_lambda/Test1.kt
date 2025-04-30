package org.example.ch4.section1_lambda

//동일한 함수 타입이 여러번 사용이 된다면 편의성을 위해서 타입 정의하고 이용할 수도..
//typealias 는 함수타입에만 사용되지 않고.. 개발자 임의 이름의 타입을 선언하는데 사용..
typealias MyFunType = (Int) -> Boolean
typealias MyInt = Int

fun main() {
    //람다함수를 선언해서 변수에 대입...
    var some = {no: Int -> println("some.... $no")}
    some(10)

    //윗줄에 의해 some 에 함수 타입이 (Int) -> Unit 으로 고정..
    //그 타입에 맞는 함수만....
//    some = { arg: String -> println("some... $arg")}//error...

    //함수 내용 여러줄 가능..
    //람다 함수내에 결과를 return 으로 사용할 수 없다..
    //맨 마지막 라인의 실행 결과값이 리턴 값..
    val some2 = { arg1: Int, arg2: Int ->
        println("$arg1, $arg2")
//        return arg1 * arg2//error...
        arg1 * arg2
    }
    println(some2(10, 20))//200

    //매개변수를 가지지 않는 람다함수는 -> 생략 가능..
    val some3 = {
        println("some3....")
        10 * 10
    }
    some3()

    //함수 타입이 선언되어 있다면.. 매개변수의 타입이 유추가 되어 매개변수에 타입 명시하지 않아도..
    val some4: (Int, Int) -> Boolean = {arg1, arg2 ->
        if(arg1 > arg2) true
        else false
    }
    val some5: MyFunType = { arg ->
        true
    }

    //매개변수를 하나 가지는 람다함수..
    //매개변수를 it 예약어로 대체.. it 은 매개변수를 지칭..
    var some6: (Int) -> Int = { arg1 -> arg1 * 10 }
    some6 = { it * 10 }

    //매개변수 하나, 타입이 유추되는 경우에 it 으로 대체..
//    val some7 = { it * 10 }//error...

    class User(val name: String, val age: Int)
    val some8: (User) -> Int = { it.age }
}