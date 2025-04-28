package org.example.ch3.section2_property.test5

import kotlin.properties.Delegates

class User {
    //초기값, 변경 순간의 callback(함수)
    var name: String by Delegates.observable("", { props, old, new ->
        println("old : $old, new : $new")
    })
}

lateinit var data1: String
class Test {
    lateinit var data2: String
    fun some(){
        //멤버 변수를 이용해야 한다.. lateinit 으로 선언되어 있다..
        //초기화 된건지 확인하고 싶다..
        //::, 코틀린에서의 참조 지정 방법...
        println(::data2.isInitialized)
    }
    //클래스 내부에 선언된 멤버 변수가 lateinit 으로 선언되어 있고..
    //이 변수를 외부에서 이용한다면.. 외부에서 직접 초기화 된것인지 확인하는 방법은 없다..
    //클래스 내에서.. 함수를 제공해서.. 그 함수를 호출해서 초기화 상태를 알게...
    fun isData2Initialized(): Boolean {
        return ::data2.isInitialized
    }

}

fun main() {
    val user = User()
    println(user.name)
    user.name = "kim"
    user.name = "lee"
    println(user.name)

    val obj = Test()
    obj.some()

    println(::data1.isInitialized)
    println(obj.isData2Initialized())

    data1 = "hello"
    obj.data2 = "world"

    println(::data1.isInitialized)
    println(obj.isData2Initialized())
}
//false
//false
//false
//true
//true