package org.example.ch2.section2_variable_type.q4

class Quiz2 {
    //자바에서 final 이 변수 앞에 선언되면.. 그 변수가 상수변수가 된다..
    //코틀린에서 final 은 상수변수와 관련없다. const 예약어로 상수변수..
    //코틀린에서도 final 제공하지만 코틀린의 final 은 상속, 오버라이드 금지..
    final val DEPARTMENT_SALE = 0
    final val DEPARTMENT_DEV = 1
    final val DEPARTMENT_MARKETING = 2

    var department = 1

    fun calcBonus() {
        //if 조건은 when 조건으로...
        when (department) {
            DEPARTMENT_SALE -> println("연봉에 10을 곱한다.")
            DEPARTMENT_DEV -> println("연봉에 50을 곱한다.")
        }
    }

}

fun main() {
    val data1 = 10
    val data2 = data1.toDouble()
    val data3 = data2.toInt()
    println("data1 : $data1, data2 : $data2, data3 : $data3")

    val str = "10"
    val intStr = str.toInt()
    val str2 = 20.toString()
    println("intStr : $intStr, str2 : $str2")

    println("${str + 10}, ${intStr + 10}")
}
