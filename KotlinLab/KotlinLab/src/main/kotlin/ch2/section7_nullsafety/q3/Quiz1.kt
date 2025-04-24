package org.example.ch2.section7.q3

class Address(var state: String?, var city: String?)

class User(val name: String, var address: Address?)

class Quiz1 {

    fun printUserInfo(user: User?) {
        if(user == null) {
            println("유저 정보가 없습니다.")
            return
        }
        val addressInfo = user.address ?: throw NullPointerException("주소 없음")
        println("${user.name}: ${addressInfo.state} ${addressInfo.city}")
    }
}

fun main() {
    Quiz1().printUserInfo(null)
    val user = User("홍길동", null)
    val address = Address(null,null)

    address.city = "성남시"
    address.state = "경기도"
    user.address = address
    println(Quiz1().printUserInfo(user))
}