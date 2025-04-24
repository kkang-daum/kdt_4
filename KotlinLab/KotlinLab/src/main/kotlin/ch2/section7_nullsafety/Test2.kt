package org.example.ch2.section7_nullsafety

fun main() {
    //일반 객체 테스트...
    //==, === 차이 없다.. 무조건 객체(주소값)비교..
    class User
    val user1: User = User()
    val user2: User? = user1
    println("user1 == user2 : ${user1 == user2}")//user1 == user2 : true
    println("user1 === user2 : ${user1 === user2}")//user1 === user2 : true

    val data1: Int = 1000
    val data2: Int = 1000
    val data3: Int? = 1000
    val data4: Int? = 1000
    //기초타입, non-null, 차이없다.. 모두 값 비교
    println("data1 == data2 : ${data1 == data2}")//true
    println("data1 === data2 : ${data1 === data2}")//true

    //기초타입, nullable, == 값, === 객체비교..
    //자바 변형시.. Integer 객체로 변경되기 때문.. 이를 boxing 이라고 한다..
    println("data3 == data4 : ${data3 == data4}")//true
    println("data3 === data4 : ${data3 === data4}")//false
}