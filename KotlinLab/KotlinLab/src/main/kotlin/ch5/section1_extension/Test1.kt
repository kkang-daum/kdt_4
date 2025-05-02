package org.example.ch5.section1_extension

class Test {
    val data: Int = 10
    fun myFun(){ println("myFun()....")}
}

//확장..위치 상관없다.. 다른 파일이어도 된다..

//프로퍼티 확장.. 가능...
//초기값 직접 대입 안되고. getter/setter 에 field 사용 불가..
//즉 이 프로퍼티에 의해 field 가 만들어지지 못하게.. getter/setter만 만들어지게..
//확장 기법이 내부적으로 함수 기법이다.. 함수의 매개변수로 확장된 것처럼 코드를 만드는 것이다.
//val Test.extensionData = 10//error

//val Test.extensionData
//    get() = field//error

val Test.extensionData
    get() = 20

//var 을 확장할 때는 get(), set() 모두 정의... field 사용 불가..
var Test.extensionData2: Int
    get() = 30
    set(value){
        //일반적으로 확장 대상이 되는 클래스에 선언된 다른 멤버 활용 로직..
    }

//MutableList 클래스가 있다.. 이 클래스로 마지막 항목을 추출하는 일이 빈번하다..
//맨 마지막 항목을 지칭하는 프로퍼티가 있으면 좋겠다..
var <T> MutableList<T>.lastItem: T
    get() = this.last()
    set(value){
        this[this.size - 1] = value
    }

fun Test.extensionFun(){
    println("extensionFun()....")
}

fun main() {
    val obj = Test()
    println("${obj.data}, ${obj.extensionData}")
    obj.myFun()
    obj.extensionFun()

    val list = mutableListOf(10, 20)
    println(list.lastItem)
    list.lastItem = 50
    println(list.lastItem)
}










