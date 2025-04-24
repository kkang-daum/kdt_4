package org.example.ch2.section7_nullsafety.q2

class Address {
    lateinit var state: String
    lateinit var city: String
}

class User {
    var name: String? = null
    var address: Address? = null
}

fun printUserInfo(user: User?) {
    user?.let {it ->
        it.address?.let {addr ->
            println("${it.name} : ${addr.state} : ${addr.city}")
        } ?: println("주소 정보가 없습니다.")
    } ?: println("유저 정보가 없습니다.")
}

fun main() {
    //유저정보
    printUserInfo(null)

    //주소정보
    val user = User()
    user.name = "홍길동"
    printUserInfo(user)

    //완료
    val address = Address()
    address.city = "성남시"
    address.state = "경기도"
    user.address = address
    printUserInfo(user)
}
