package org.example.ch2.sec4_control

import java.text.SimpleDateFormat

fun saySeason(data: String): String {
    val date = SimpleDateFormat("yyyy-MM-dd").parse(data)
    return when(date.month + 1) {
        12, 1, 2 -> "겨울이군요"
        3, 4, 5 -> "봄이군요"
        6, 7, 8 -> "여름이군요"
        9, 10, 11 ->"가을이군요"
        else -> "잘못된 날짜 형식이에요"
    }
}

fun main() {
    println(saySeason("2025-04-30"))
    println(saySeason("2025-12-30"))
}