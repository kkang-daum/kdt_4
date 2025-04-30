package org.example.ch3.section10_value

//value 클래스 선언...
//@JvmInline : Java 로 변경시에.. Id 클래스로 int 테이터가 wrapping 되는 부분을 해제시켜라..
@JvmInline
value class Id(val value: Int)

@JvmInline
//value class Id2()//error... 프로퍼티 하나가 무조건 주 생성자에 선언되어야 한다..
//value class Id2 {//property 는 주 생성자내에 선언되어야 한다..클래스 바디에는 선언할 수 없다
//    val value = 10
//}
//value class Id2(val value: Int, val value2: Int)//하나만 선언되어야 한다..

//조건이 주생성자에 단 하나의 프로퍼티만 선언되어야 하고 바디에 프로퍼티가 선언되면 안된다..
//일반적으로 클래스내에 들어가는 init{}, 생성자, 함수는 추가 가능하다..
//이렇게 클래스를 선언하면.. @JvmInline 을 추가했다고 하더라도 자바에서 그냥 객체이다..
//value 를 선언한 목적과 의도에 위배된다..
//==>value 클래스는 가급적 간단하게.. 값 하나만 표현하도록 권장..
value class Id2(val value: Int){
    init {
        println("hello")
    }
    constructor(arg1: String): this(10)
    fun someFun(){}
}

fun sayHello(id: Id){

}
fun main() {
    sayHello(Id(0))
}