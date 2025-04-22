package org.example.ch2.section3_function

fun main() {
    fun sayHello(name: String = "lee", no: Int = 0){
        println("Hello $name, no : $no")
    }

    //갯수와 순서를 맞추어서..
    sayHello("kim", 10)
    //default 값이 선언되어 있어서.. optional.. 생략 가능하다..
    sayHello()
    //이름을 명시해서 데이터를 지정하면 순서가 의미가 없다..
    sayHello(no = 10, name = "kim")


    fun myFun(arg1: String, arg2: Int = 10, arg3: String = "hello", arg4: Int){}
    //순서를 맞추어서..
    myFun("aa", 20, "bb", 30)
    //이름을 지정해서..
    myFun(arg4 = 40, arg3 = "bb", arg1 = "aa", arg2 = 30)
//    myFun("aa", 10)//error..
    myFun(arg1 = "aa", arg4 = 10)
}