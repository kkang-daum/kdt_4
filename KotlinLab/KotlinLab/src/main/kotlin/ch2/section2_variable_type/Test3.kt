package org.example.ch2.section2_variable_type.three

//코틀린의 모든 변수는 객체이다..
val intData: Int = 10//기초 타입처럼 데이터를 직접 삽입했지만.. Int 클래스의 객체이다..
var result = intData - 5//result 는 Int 타입으로 유추... 고정..

fun main() {
    result = intData.minus(5)

    //숫자타입..
    val a1: Int = 123//정수의 기본형..
    val a2: Long = 10L
    val a3: Double = 10.0//실수의 기본형
    val a4: Float = 10.0f

    //코드의 명료성을 위해서.. 숫자 사이에 _ 추가 가능..
    //_는 코드에서만 남아있다. 컴파일 하면 사라진다..
    val a5 = 10000_00_0_000_000

    //string template................
    val str1 = "hello \n world"
    val str2 = """
        hello
        world
        aaa
            bbb
    """
    println(str1)
    println(str2)

    fun sum(no: Int): Int {
        var sum = 0
        for(i in 1..no){
            sum += i
        }
        return sum
    }
    val name = "홍길동"
    //name : 홍길동, 홍길동 , 55, 70
    println("name : ${name}, $name , ${sum(10)}, ${10 + 20 * 3}")
}

