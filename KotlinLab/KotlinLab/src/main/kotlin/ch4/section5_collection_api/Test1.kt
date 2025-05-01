package org.example.ch4.section5_collection_api

fun main() {
    println("forEach....... for 대체용.. 람다함수 순차적으로 데이터 전달하면서 실행")
    val list1 = listOf(12, 8, 9, 20)
    list1.forEach { println(it) }

    println("fold..... 이전 실행 결과를 자동으로 그 다음 실행시 매개변수로 전달..")
    //초기값 지정 가능.. 10
    //total : 이전 결과값...
    var result = list1.fold(10, { total, next ->
        println("$total....$next")
        total + next
    })
    println("result : $result")

    println("filter................")
    list1.filter { it > 10 }
        .forEach { println(it) }

    println("map........")
    list1.map { it * 10 }//4개..
        .filter { it > 100 }
        .forEach { println(it) }
}