package org.example.ch2.quiz.q4

/**
 * ch2/sec7_nullSafety
 * null-safety 활용
 */

class Address {
    lateinit var state: String
    lateinit var city : String
}

class User{
    var name : String? = null
    var address : Address? = null
}

fun printUserInfo(user:User?){
    if(user != null){
        if(user.address != null){
            println("${user.name} : ${user.address!!.state} ${user.address!!.city}")
        } else{
            throw NullPointerException("주소없음")
        }
    }else{
        println("유저 정보가 없습니다.")
    }
}

fun main() {
    printUserInfo(null)

    val user = User()
    user.name = "홍길동"

    val address = Address()
    address.city = "성남시"
    address.state = "경기도"
    user.address = address
    printUserInfo(user)

}