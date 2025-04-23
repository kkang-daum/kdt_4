package org.example.ch2.section3_function

class Math {
    fun plus(no1: Int = 0, no2: Int = 0, no3: Int = 0): Int = no1 + no2 + no3
}

fun main() {
    val math = Math()
    println(math.plus())
    println(math.plus(10))
    println(math.plus(10, 20))
    println(math.plus(10, 20, 30))
}