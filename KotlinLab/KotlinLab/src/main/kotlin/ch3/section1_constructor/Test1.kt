package org.example.ch3.section1_constructor

fun main() {
    //주 생성자 선언...
    //개발자가 명시적으로 생성자를 추가하지 않으면 컴파일러에 의해 주생성자로, default 생성자 추가
    //주 생성자의 constructor 는 생략 가능..
    class MyClass
    class MyClass2()
    class MyClass3 constructor()

    val obj1 = MyClass()
    val obj2 = MyClass2()
    val obj3 = MyClass3()

    //생성자는 매개변수를 가질 수 있다.. 매개변수에 default 값 명시가능, 옵셔널로 사용 가능..
    class User1(name: String, age: Int = 0)

    var user1 = User1("kim", 20)
    user1 = User1("lee")

    //주 생성자의 실행 영역..
    //class header 에 추가됨으로.. 실행영역이 필요하면 init
    class User2(name: String){
        init {
            println("i am init...")
        }
    }
    var user2 = User2("kim")//i am init...
    user2 = User2("lee")//i am init...

    //주 생성자의 매개변수의 이용 범위.. scope.......................
    class User3(name: String){
        val data = name//멤버변수 선언위치에.. 초기화 용도로 사용 가능..
        init {
            println("init, $name")
        }
        fun someFun(){
//            println("someFun...$name")//error...
        }
    }


    //주 생성자의 매개변수로.. 멤버변수 초기화................................
    class User4(name: String, age: Int){
        var myName: String
        var myAge: Int
        init {
            myName = name
            myAge = age
        }
    }
    //위의 스타일은 자바 스타일을 그대로 코틀린으로...
    //위와 동일하게.. 아래처럼...
    class User5(val name: String, val age: Int){
        fun someFun(){
            println("$name, $age")
        }
    }
}