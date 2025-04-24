package org.example.ch2.section5_collection

fun filterGreaterThan(array: IntArray, no: Int): IntArray {
    //갯수가 가변..
    val list = mutableListOf<Int>()//큰 수만 담기 위해서..

    for(value in array){
        if(value > no){
            list.add(value)
        }
    }
    return list.toIntArray()
}

fun main() {
    val resultArray = filterGreaterThan(intArrayOf(1, 5, 10, 20, 25, 30), 15)

    println(resultArray.joinToString())


}