package org.example.ch2.section2_variable_type.Quiz.q2

var X = 83
var Y = 100
var Z = 0
fun main() {
    println("[ 교환 전 ]")
    println("javaScore : $X")
    println("pythonScore : $Y")
    println("-------------------")

    Z = X
    X = Y
    Y = Z

    println("[ 교환 후 ]")
    println("javaScore : $X")
    println("pythonScore : $Y")
}