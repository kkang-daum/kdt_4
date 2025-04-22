package org.example.ch2.section2_variable_type

var javaScore = 90
var pythonScore = 80

fun main() {
    println("[교환전]")
    println("javaScore : $javaScore")
    println("pythonScore : ${pythonScore}")

    val temp = javaScore
    javaScore = pythonScore
    pythonScore= temp

    println("[교환후]")
    println("javaScore : $javaScore")
    println("pythonScore : ${pythonScore}")
}