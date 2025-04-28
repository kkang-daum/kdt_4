package org.example.ch3.section2_property.test4

//by - 예약어..
//뭔가의 행위를 위임시킬 때 쓰는 예약어.. delegation pattern 구현을 도와주기 위해서
//만든 예약어..
val data1: String by lazy {
    println("data1 lazy.......")
    //마지막 라인의 실행 결과 값이 초기값...
    "hello"
}

class User1 {
    val name: String by lazy {
        println("name lazy......")
        "kim"
    }
    val age: Int by lazy {
        println("age lazy.....")
        0
    }
    init {
        println("init....")
    }
}

fun main() {
    val user = User1()
    println("name before...")
    println(user.name)
    println("name after...")
    println("name before...")
    println(user.name)
    println("name after...")

    println("age before...")
    println(user.age)
    println("age after...")
}
//init....
//name before...
//name lazy......
//kim
//name after...
//name before...
//kim
//name after...
//age before...
//age lazy.....
//0
//age after...