package org.example.ch2.section3_basic_syntax

class Quiz2 {
    fun calcBonus(salary: Int, bonusRate: Double)
    = if (salary <= 0 || bonusRate <= 0) "잘못된 데이터를 입력했습니다" else "지급받을 보너스는 ${salary * bonusRate}원 입니다."
}

fun main() {
    println(Quiz2().calcBonus(1000, 0.25))
}