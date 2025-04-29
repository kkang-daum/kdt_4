package org.example.ch3.section6_data

//data 클래스 선언 문법...
//주생성자를 가져야 하며.. 하나 이상의 매개변수 선언되어야 한다..
//data class User1()//error

//주 생성자의 모든 매개변수는 클래스의 멤버변수로 선언되어야 한다..
//data class User1(name: String)//error

//data 클래스는 상속으로 서브 클래스를 만들 수 없다..
//상속과 관련된 예약어를 같이 사용할 수 없다..
//open, abstract, sealed...
data class User1(val name: String, val no: Int)

data class User2(val name: String, val age: Int){
    //data 클래스의 작성 규칙은 클래스 선언위치에 있는거다..
    //얼마든지.. 클래스 바디 가질 수 있고. 바디에 변수, 함수, 생성자 추가가능하다..
    //물론 data 클래스에 자동 지원되는 사항에서 body 내용은 제외된다..
    var address: String = "seoul"
    fun someFun(){}
    constructor(name: String): this(name, 0)
}

//equals()............................................
class User(val no: Int, val name: String)
data class UserData(val no: Int, val name: String){
    var email: String? = null
    constructor(no: Int, name: String, email: String): this(no, name){
        this.email = email
    }
}

fun main() {
    val user1 = User(1, "kim")
    val user2 = User(1, "kim")
    val user3 = UserData(1, "kim")
    val user4 = UserData(1, "kim")

    //false, true, false
    //일반 클래스의 equals 함수는 개발자가 명시적으로 equals 를 오버라이드 받지 않았음으로
    //Any 의 equals 함수를 이용..Any 의 equals 함수는 객체 비교이다..
    //data 클래스는 우리가 equals 함수를 추가하지 않아도 자동으로 추가.. 값 비교하게 되어 있다.
    println("${user1.equals(user2)}, ${user3.equals(user4)}, ${user1.equals(user3)}")

    val user5 = UserData(1, "kim", "a@a.com")
    val user6 = UserData(1, "kim", "b@b.com")

    //true, true
    //data 클래스는 주 생성자의 멤버만 비교하게 equals 함수가 선언..
    println("${user5.equals(user6)}, ${user5.equals(user3)}")

    //data 클래스의 equals 함수가 값 비교로 구현되어 있어 편하지만..
    //경우에 따라 객체비교가 필요한 경우도 있다..
    //== : 값 비교, === : 객체 비교.. 일반클래스는 모두 객체 비교..
    //false, false, true, false
    //자바에서는 == 밖에 없다..
    //코틀린에서 == => equals(), === => ==
    println("${user1 == user2}, ${user1 === user2}, ${user3 == user4}, ${user3 === user4}")


    //toString().....................................................
    //Any 에 선언된 toString 이 이용..
    println(user1.toString())//org.example.ch3.section6_data.User@66d2e7d9
    println(user3.toString())//UserData(no=1, name=kim)
    println(user5.toString())//UserData(no=1, name=kim)




}