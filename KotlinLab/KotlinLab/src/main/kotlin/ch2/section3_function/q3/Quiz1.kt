package ch2.section3_function

class Math {
    fun plus(): Int = 0
    fun plus(no1: Int): Int = no1
    fun plus(no1: Int, no2: Int): Int = no1 + no2
    fun plus(no1: Int, no2: Int, no3: Int): Int = no1 + no2 + no3
}

fun main() {
    val m = Math()
    println(m.plus())
    println(m.plus(10))
    println(m.plus(10, 20))
    println(m.plus(10, 20, 30))
}