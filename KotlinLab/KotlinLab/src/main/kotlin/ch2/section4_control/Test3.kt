package org.example.ch2.section4_control

fun main() {
    //1부터 10까지..
    for(i in 1..10){
        print("$i ")
    }
    println()
    //1부터 10 미만..
    for(i in 1 until 10){
        print("$i ")
    }
    println()
    //1부터 10까지, 2씩 증가시켜서..
    for(i in 1..10 step 2){
        print("$i ")
    }
    println()
    //10부터 1까지 감소시키면서, 2씩 감소
    for(i in 10 downTo 1 step 2){
        print("$i ")
    }

    println()
    //배열 변수 선언...
    val array = arrayOf("hello", "world")
    //array 갯수 만큼 반복, 한번 반복될때 마다.. 값이 value 에 저장..
    for(value in array){
        print("$value ")
    }
    println()
    //값이 아닌, 반복 index 가 필요한 경우...
    for(index in array.indices){
        print("$index ")
    }
    println()
    //값과 index 가 모두 필요한 경우..
    for((i, v) in array.withIndex()){
        print("$i - $v ,")
    }
}