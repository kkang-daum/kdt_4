package org.example.ch2.section4_control

fun main() {
    //while.....
    var x = 0
    var sum = 0
    while (x < 10){
        sum += ++x
    }
    println(sum)

    var x2 = 0
    var sum2 = 0
    while (true){
        sum2 += ++ x2
        if(x2 == 10) break
    }
    println(sum2)

    aaa@ for(i in 1..3){
        for(j in 1..3){
            if(j > 1) break@aaa
            println("$i $j")
        }
    }
}