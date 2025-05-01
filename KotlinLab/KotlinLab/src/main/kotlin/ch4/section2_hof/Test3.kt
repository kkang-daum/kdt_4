package org.example.ch4.section2_hof

fun main() {

    class User(){
        var name: String? = null
        var age: Int? = null
        constructor(name: String, age: Int): this(){
            this.name = name
            this.age = age
        }
        fun sayHello(){
            name = "lee"
            sayInfo()
        }
        fun sayInfo(){}
    }

    //User 객체의 멤버에 반복적으로 접근해야 한다..
    val user = User()
    user.name = "kim"
    user.age = 20
    user.sayHello()
    user.sayInfo()

    //run 을 이용......................
    //{ } 이 User 클래스내에 선언된 효과가 난다..
    val runResult = user.run {
        name = "kim"
        age = 20
        sayHello()
        sayInfo()
        10
    }
    println(runResult)//run 에 대입한 람다함수의 리턴값이 run 의 리턴값..

    //apply 의 리턴 값은.. applay 에 적용한 객체
    val user1 = user.apply {
        name = "lee"
        age = 20
        sayHello()
        sayInfo()
    }
    //user: lee, user1: lee
    println("user: ${user.name}, user1: ${user1.name}")

    //let...................
    //user 객체를 람다의 매개변수로 전달..
    user.let {
        println("${it.name}, ${it.age}")
    }

    //with..........................
    with(user){
        name = "park"
        age = 30
    }
}