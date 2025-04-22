package org.example.ch2.section2_variable_type

//상수변수.. 코틀린에서는 const 예약어로..
//top level 변수만 가능..
const val DEPARTMENT_SALE = 0
const val DEPARTMENT_DEV = 1
const val DEPARTMENT_MARKETING = 2

class Quiz2 {
    var department = 1

    fun calcBonus() {
        if(department == DEPARTMENT_SALE){
            println("연봉에 10을 곱합니다.")
        }else if(department == DEPARTMENT_DEV){
            println("연봉에 50을 곱합니다.")
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
    val str2 = intStr.toString()
    println("int str : $intStr, str2 : $str2")
    println("${str + 10}, ${intStr + 10}")
}