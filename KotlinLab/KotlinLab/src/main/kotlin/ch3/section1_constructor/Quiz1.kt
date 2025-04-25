package org.example.ch3.section1_constructor.quiz1

class User(var name: String?, var age: Int = 0){
    constructor(): this(null, 0)
    constructor(name: String): this(name, 0)
    fun printUserInfo(){
        println("name : $name, age :  $age")
    }
}

fun main() {
    val user1 = User()
    user1.printUserInfo()

    val user2 = User("kim")
    user2.printUserInfo()

    val user3 = User("kim", 30)
    user3.printUserInfo()
}