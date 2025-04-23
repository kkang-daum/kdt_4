package org.example.ch2.quiz

/** sec4_control/test4
 *  반복문 활용 quiz
 *
 *  정수를 입력받고 prime number 출력하기
 */

fun main() {
    print("정수를 입력하세요: ")
    val num = readln().toIntOrNull() ?: 0

    if (num < 2) {
        println("$num 은/는 소수가 아닙니다.")
        return
    }

    for (i in 2..num) {
        var isPrime = true

        for (j in 2..Math.sqrt(i.toDouble()).toInt()) {
            if (i % j == 0) {
                isPrime = false
                break
            }
        }

        if (isPrime) {
            print("$i ")
        }
    }
}