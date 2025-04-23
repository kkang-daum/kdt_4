package org.example.ch2.section4_control


fun main() {
    println("양의 정수를 입력 하세요.")

    //toIntOrNull() - 입력한 문자열을 Int 로 변형.. int 변형이 안되면 null 리턴..
    // ?: 0 - 앞부분이 null 이라면 0 값을 이용하겠다..
    val num = readln().toIntOrNull() ?: 0

    for(no in 2..num){
        if(no == 2)
            print("$no ")
        else {
            var isPrime = true
            for(divisor in 2..no/2){
                if(no % divisor == 0){
                    isPrime = false
                    break
                }
            }
            if(isPrime)
                print("$no ")
        }
    }
}