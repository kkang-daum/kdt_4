package org.example.ch2.section3_function

//매개변수 갯수 만큼 업무 반복
fun loopPrint(no: Int = 1){
    var count = 1
    while (true){
        println("hello...")
        if(no == count) return
        else count++
    }
}

//위의 로직을 재귀 알고리즘으로 구현...
fun recPrint(no: Int = 1, count: Int = 1){
    println("recPrint...")
    return if(no == count) return else recPrint(no - 1, count)
}

tailrec fun tailrecPrint(no: Int = 1, count: Int = 1){
    println("tailrecPrint...")
    return if(no == count) return else tailrecPrint(no - 1, count)
}

//tailrec 를 추가했다고 모든 함수가 재귀해제가 아니라..
//이 함수의 마지막 실행 구문이 자신 함수 호출 구문만 가능..
//아래의 함수는 재귀 해제되지 않는다..
tailrec fun sum(n: Int): Int {
    if(n < 0) return n
    else return n + sum(n - 1)
}

fun main() {
    loopPrint(3)
    recPrint(3)
    tailrecPrint(3)

    println(sum(3))
}