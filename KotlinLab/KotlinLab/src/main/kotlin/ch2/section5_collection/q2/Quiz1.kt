package org.example.ch2.section5_collection

fun filterGreaterThan1(intArray: IntArray, num: Int): List<Int> =
    intArray.toMutableList().apply { removeIf { it <= num } }


fun filterGreaterThan2(intArray: IntArray, num: Int): List<Int> {
    val result = mutableListOf<Int>()

    for (i in intArray.indices)
        if (intArray[i] > num) result.add(intArray[i])
    return result
}



fun main() {
    println(filterGreaterThan1(intArrayOf(1, 5, 10, 15, 20, 25, 30), 20))
    println(filterGreaterThan2(intArrayOf(1, 5, 10, 15, 20, 25, 30), 20))
}
