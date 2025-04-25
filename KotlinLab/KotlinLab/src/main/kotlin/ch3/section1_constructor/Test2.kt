package org.example.ch3.section1_constructor

fun main() {
    //보조 생성자 선언...
    class User {
        //보조생성자가 선언되면.. 컴파일러에 의해 주생성자는 추가가 안된다..
        //물론.. 개발자가 추가할 수는 있다..
        //생성자도.. body 가 없다면 {} 생략 가능하다..
        constructor(name: String)
    }

    //생성자 오버로딩..
    //주 생성자는 1개만 선언 가능하지만..
    //보조 생성자는 오버로딩 지원, 여러개 선언 가능..
    class User2 {
        constructor()
        constructor(name: String)
        constructor(name: String, age: Int)
    }

    //보조 생성자와 init........................
    //보조 생성자로 객체를 생성해도 init 부분은 실행이 된다..
    //init 이 먼저 실행되고 생성자의 바디가 실행된다..
    class User3 {
        init {
            println("init....")
        }
        constructor(){
            println("constructor...")
        }
    }
    User3()
    //init....
    //constructor...

    //보조 생성자의 매개변수....................................
    class User4 {
        //보조 생성자의 매개변수에는 var, val 을 추가해서 멤버 변수로 만들 수 없다..
        constructor(name: String){
            println(name)
        }
//        init {
//            println(name)//error
//        }
//        val data = name //error
//        fun someFun() {
//            println(name)//error
//        }
    }
}