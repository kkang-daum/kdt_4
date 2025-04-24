package org.example.ch2.section5_collection.q1

fun filterGreaterThan(intArray: IntArray, num: Int): IntArray{
    val answer = mutableListOf<Int>()
    intArray.forEach {
        if(it > num) answer.add(it)
    }
    return answer.toIntArray()
}

fun main() {
    println(filterGreaterThan(intArrayOf(1, 5, 10, 15, 20, 25, 30), 15).joinToString(","))
}