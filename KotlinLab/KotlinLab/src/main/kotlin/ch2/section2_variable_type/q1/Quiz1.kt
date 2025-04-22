package org.example.ch2.section2_variable_type.q1

var javaScore = 83
var pythonScore = 100


fun swap(){
    var temp = pythonScore
    pythonScore = javaScore
    javaScore = temp
}

fun main() {
    println("[ 교환 전 ]")
    println("JavaScore : $javaScore")
    println("pythonScore : $pythonScore")
    println("--------------")
    swap()
    println("[ 교환 후 ]")
    println("JavaScore : $javaScore")
    println("pythonScore : $pythonScore")
}

