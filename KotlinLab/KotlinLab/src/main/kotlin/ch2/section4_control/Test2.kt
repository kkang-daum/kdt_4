package org.example.ch2.section4_control

fun main() {
    val a = 1

    fun testWhen(data: Any) {
        //data 의 값이 정수이외에 다양한 타입의 데이터로 조건 가능...
        //값이 아닌.. 타입으로 조건 명시 가능...
        when (data) {
            1 -> println("1")
            "hello" -> println("hello")
            is Boolean -> {
                println("data is Boolean type")
            }
            else -> {

            }
        }
    }

    //or....
    when(a){
        10, 20, 30 -> println("10, 20, 30")
        40, 50 -> println("40, 50")
        10 + 20 -> println("30")//연산에 의한 실행 결과로 조건.. 함수 호출하고 리턴 값 등..
    }

    //범위.....
    when(a){
        in 1..10 -> println("1..10")
        !in 11..20 -> println("11..20")
    }

    //expression 으로 활용 가능..
    val result = when(a){
        1 -> "1"
        2 -> "2"
        3 -> {
            println("3")
            3//마지막 라인의 실행 결과값...
        }
        else -> {
            //expression 으로 활용된다면.. else 생략 불가하다..
            0
        }
    }
}