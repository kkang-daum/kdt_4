package org.example.ch2.sec4_control.q3

import java.text.SimpleDateFormat
import java.util.Calendar



fun saySeason(inputDate: String): String {
    val date = SimpleDateFormat("yyyy-MM-dd").parse(inputDate)

    val calendar = Calendar.getInstance()
    calendar.time = date

    val month = calendar.get(Calendar.MONTH) + 1

    return when (month) {
        12, 1, 2 -> "겨울이군요"
        3,4,5 -> "봄이군요"
        6,7,8 -> "여름이군요"
        9,10,11 ->  "가을이군요"
        else -> "잘못된 입력이군요"
    }
}

fun main() {
    println(saySeason("2025-04-30"))
    println(saySeason("2025-12-30"))
}