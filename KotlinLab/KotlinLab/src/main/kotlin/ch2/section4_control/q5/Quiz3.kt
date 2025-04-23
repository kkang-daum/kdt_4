package org.example.ch2.sec4_control

class Quiz3 {
}

fun main() {
    print("양수를 입력하세요 : ")
    val inputNumber = readln().toIntOrNull() ?: 0

    for (primeNumber in calculatePrimeNumber(inputNumber)) print(" $primeNumber ")
}

fun calculatePrimeNumber(input: Int): Array<Int> {
    val primeNumbers = mutableListOf<Int>()

    for (number in 1..input) {
        if (isPrime(number)) primeNumbers.add(number)
    }

    return primeNumbers.toTypedArray()
}

fun isPrime(number: Int): Boolean {
    if (number < 2) return false

    for (divider in 2 until number) {
        if (number % divider == 0) return false
    }
    return true
}