package likelion.study.ch2.section3_function.q1

class Math {
    fun plus(vararg no: Int): Int = no.sum()
}

fun main() {
    val math = Math()
    println(math.plus())
    println(math.plus(10))
    println(math.plus(10, 20))
    println(math.plus(10, 20, 30))
}
