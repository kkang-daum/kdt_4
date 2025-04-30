package org.example.ch3.section9_object

class Outer {
    var no: Int = 10
    fun outerFun(){
        println("outerFun....")
    }

    //nested class 는 자바의 static inner class 이다..
    //static 이다.. object member 이용 불가하다..
    class Nested {
        var name: String = "kim"
        fun myfun(){
            name = "kim"
//            no = 20//error...
//            outerFun()//error...
        }
    }
}

class Outer2 {
    var no: Int = 10
    fun outerFun(){
        println("outerFun....")
    }
    //inner class 는 outer 의 object member 로 추가된다..
    //outer 의 object member 이용이 가능핟..
    inner class Nested2 {
        var name: String = "kim"
        fun myfun(){
            name = "kim"
            no = 20//ok
            outerFun()//ok...
        }
    }
}

fun main() {
    //netsted class 는 outer 의 static member 임으로.. outer 클래스명으로 바로 접근
    val obj = Outer.Nested()
    obj.myfun()

    //object member 이다.. 객체 생성한 다음에 접근해야 한다..
//    val obj2 = Outer2.Nested2()//error...
    val obj3 = Outer2().Nested2()
}