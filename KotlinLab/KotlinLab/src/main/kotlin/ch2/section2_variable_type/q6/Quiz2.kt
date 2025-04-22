package org.example.ch2.section2.q6

//val 은 상수 변수 아니다.. 값 변경은 불가능하지만.. 참조해서 이용할때 동일 값이 나온다고
//보장할 수 없다..
val departMent_sale: Int = 0
val department_dev: Int = 1
val department_marketing: Int = 2

val department: Int = 1

fun calcBonus() {
    if (department == departMent_sale) {
        println("연봉에 10을 곱한다")
    } else if (department == department_dev) {
        println("연봉에 50을 곱한다")
    }
}
fun main() {
    val data1: Int = 10;
    val data2: Double = data1.toDouble()
    val data3: Int = data2.toInt()

    println("data1: $data1, data2: $data2, data3: $data3")

    val str = 10
    val intStr = str.toInt()
    val str2 = 20.toString()

    println("int str : $intStr, str2 : $str2")
    println("${str + 10}, $(intStr + 10)")
}