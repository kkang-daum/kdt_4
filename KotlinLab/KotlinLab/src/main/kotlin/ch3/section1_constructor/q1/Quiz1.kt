package org.example.ch3.sec1_constructor.q1

class User(
    var name: String? = null,
    var age: Int = 0
) {

//    constructor(name: String) : this()
//    constructor(name: String, age: Int) : this() {
//        this.name = name
//        this.age = age
//    }

    fun printUserInfo(): Unit {
        println("name : $name, age : $age")
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