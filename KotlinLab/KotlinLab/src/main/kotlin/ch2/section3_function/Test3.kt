package org.example.ch2.section3_function

//함수를 중위 표현식으로 사용하려면...
//infix 예약어로 선언되어야 하며...
//객체의 멤버 함수이거나... 확장 함수만 가능하다..
//매개변수 하나짜리...

class MyClass {
    infix fun myFun(a: Int){
        println("MyClass... myFun call.... $a")
    }
}
//클래스 외부에서.. 특정 클래스에 멤버 추가가 가능하다..
//이 기법이 extension 기법이라고 한다..
//우리가 만든 클래스든, 남이 만든 클래스든, 다 추가할 수 있다..
infix fun MyClass.myFun2(a: Int){ }
infix fun Int.myFun(x: Int){}

fun main() {
    val obj = MyClass()
    obj.myFun(10)
    obj myFun 10

    obj.myFun2(10)
    obj myFun2 10

    val no: Int = 10
    no.myFun(10)
    no myFun 10

    //가변 인수..
    fun someFun(arg1: Int, vararg arg2: Int){
        for(a in arg2){
            println(a)
        }
    }
    someFun(10)
    someFun(10, 20, 30)
    someFun(10, 20, 30, 40, 50, 60)
}