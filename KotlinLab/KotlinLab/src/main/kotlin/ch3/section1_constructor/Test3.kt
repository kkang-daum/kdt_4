package org.example.ch3.section1_constructor

class User(name: String) {
    init {
        println("init....")
    }
    constructor(name: String, age: Int): this(name){
        println("constructor....$name, $age")
    }
}

class User2(name: String){
    constructor(name: String, age: Int): this(name)
    //보조생성자에서 자신의 다른 보조 생성자를 호출을 할 수는 있다..
    //최종 주 생성자만 호출되면 된다..
    constructor(name: String, age: Int, email: String): this(name, age)
}

fun main() {
    User("kim")
    //init....

    User("kim", 30)
    //init....
    //constructor....kim, 30
}