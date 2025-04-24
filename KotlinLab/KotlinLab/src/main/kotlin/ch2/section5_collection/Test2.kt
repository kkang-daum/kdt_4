package org.example.ch2.section5_collection

fun main() {
    val list1 = listOf("hello", "world")//List<String>
    val list2 = mutableListOf("hello", "world")//MutableList<String>

    println("${list1.get(0)}, ${list1.get(1)}")
    println("${list2.get(0)}, ${list2.get(1)}")

    list2.add("aaa")
    list2.set(0, "bbb")
    //immutable.. List 에는 add(), set() 함수가 없다.. 변경 추가 불가하다..
//    list1.add("aaa")//error
//    list1.set(0, "aaa")//error

    val map = mapOf<String, String>(Pair("1", "hello"), Pair("2", "world"))
    val map2 = mutableMapOf("1" to "hello", "2" to "world")
    println("${map.get("1")}, ${map2.get("1")}")
//    map.put("3", "aaa")//error..
    map2.put("3", "aaa")

    val iterator = list1.iterator()
    val iterator2 = map.iterator()

    while (iterator.hasNext()){
        println(iterator.next())
    }
    while (iterator2.hasNext()){
        val entry = iterator2.next()
        println("${entry.key} - ${entry.value}")
    }
}