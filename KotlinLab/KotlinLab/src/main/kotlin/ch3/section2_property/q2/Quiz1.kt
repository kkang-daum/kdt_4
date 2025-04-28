package org.example.ch3.sec2_property

class User {
    lateinit var firstName: String
    lateinit var lastName: String
    var age: Int = 0
        set(value) {
            if(value >= 0) field = value
            else throw IllegalArgumentException("나이는 음수가 될 수 없습니다.")
        }
    var password: String = ""
        set(value) {
            if(value.length < 8) {
                throw IllegalArgumentException("8자 이상의 패스워드 필요")
            } else {
                field = value
            }
        }

    fun isAdult(): Boolean {
        return age >= 18
    }
    fun getFullName(): String {
        return "$firstName $lastName"
    }
}

fun main() {
    try {
        val user = User()
        // user.age = -1    //error
        // user.password = "aa" //error
        user.age = 10
        user.firstName = "길동"
        user.lastName = "홍"

        println(user.isAdult())
        println(user.getFullName())
    }
    catch (e: Exception) {
        e.printStackTrace()
    }
}