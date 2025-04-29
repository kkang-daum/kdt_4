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


    //destructing assignment(구조분해할당)........................
    val no1 = user3.component1()
    val name1 = user3.component2()
//    user3.component3()//error.. 주 생성자의 매개변수 갯수만큼 만들어 진다..
    println("$no1, $name1")//1, kim

    //한꺼번에 여러 데이터 추출..
    val (no2, name2) = user3
    println("$no2, $name2")//1, kim
    //순서로 하는 것이다. 변수명과 관련 없다..
    val (a, b) = user3
    println("$a, $b")//1, kim
    //멤버 변수가 좀 많다.. 모두 뽑고 싶은것이 아니라.. 2번째, 4번째만 뽑고 싶다..
    val (_, c) = user3
    println("$c")//kim


    //data 클래스가 아닌 list 도 가능하기는 하다........
    val list = listOf(1, 2, 3, 4, 5)
    val (d1, d2) = list
    println("$d1, $d2")//1, 2

    val (e1, e2) = list.drop(1)
    println("$e1, $e2")//2, 3

    val (_, f1, _, f2) = list
    println("$f1, $f2")//2, 4

    val g1 = list.component5()
    println("$g1")//5


    //어떤 함수에서 데이터를 여러개를 리턴시키고자 한다면? 마치 아래처럼...
    //이를 흔히 tuple 기법이라고 하는데.. 코틀린은 지원하지 않는다.
//    fun someFun(): (Int, Int, Int, Int) {
//        return (10, 20, 30, 40)
//    }
//    val (f1, f2, f3, f4) = someFun()

    //data 클래스를 이용해서 비슷하게 흉내낼 수 있다..
    data class Datas(var data1: Int, var data2: Int, var data3: Int)
    fun getDatas(): Datas {
        return Datas(10, 20, 30)
    }
    val (k1, k2, k3) = getDatas()
    println("$k1, $k2, $k3")//10, 20, 30

    //아래처럼 해도 된다..
    fun getDatas2(): Array<Int>{
        return arrayOf(10, 20, 30)
    }
    val (j1, j2, j3) = getDatas2()
    println("$j1, $j2, $j3")//10, 20, 30

}