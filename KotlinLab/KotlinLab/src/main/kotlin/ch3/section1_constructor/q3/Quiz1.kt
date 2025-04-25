package ch3.section1_constructor.Quiz1

class User(var name: String? = null, var age: Int = 0){
    fun printUserInfo(){
        println("name: $name, age: $age")
    }
}

fun main(){
    val user1 = User()
    val user2 = User("kim")
    val user3 = User("kim", 30)

    user1.printUserInfo()
    user2.printUserInfo()
    user3.printUserInfo()
}