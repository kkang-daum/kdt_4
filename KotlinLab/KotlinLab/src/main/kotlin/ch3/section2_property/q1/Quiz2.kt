package org.example.ch3.quiz

class User {
    var firstName: String = ""
    var lastName: String = ""
    var password: String = ""
        set(value) {
            if (value.length < 8) throw IllegalArgumentException("8자 이상의 패스워드 필요")
            else field = value
        }

    var age: Int = 0
        set(value) {
            if (value < 0) throw IllegalArgumentException("나이는 음수가 될 수 없습니다.")
            else field = value
        }

    val isAdult: Boolean
        get() = age >= 18
    val fullName: String
        get() = firstName + lastName
}

fun main() {
    try {
        val user = User()
//        user.age = -1
//        user.password = "aa"
        user.age = 10
        user.firstName = "홍"
        user.lastName = "갈동"

        println(user.isAdult)
        println(user.fullName)
    }catch (e: Exception) {
        e.printStackTrace()
    }
}