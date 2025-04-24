package org.example.ch2.section7_nullsafety

class Address {
    var state: String? = null
    var city: String? = null
}
class User {
    var name: String? = null
    var address: Address? = null
}

fun printUserInfo(user: User?){
    user?.run {
        //not null 일때 실행..
        address!!.run {
            //user - not null, address - not null
            println("${user.name} ${user.address?.state}")
        }
    } ?: run {
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