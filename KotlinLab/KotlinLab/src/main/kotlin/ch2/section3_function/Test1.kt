package org.example.ch2.section3_function

fun main() {
    fun sum(a: Int, b: Int): Int {
        var sum = 0
        //함수 내에 함수 선언이 가능하다..
        //함수 안에 선언했음으로.. 이 내부 함수는 outer 함수의 local 함수이다..
        //outer 에서 호출해서 이용하던가.. 아니면.. 이 함수를 외부에 리턴시키던가..
        fun calSum(){
            for(i in a..b){
                sum += i
            }
        }
        calSum()
        return sum
    }
    val result = sum(1, 10)
    println(result)

    //single expression function... 한줄짜리 함수.. 결과가 리턴되는 함수..
    fun some(a: Int, b: Int): Int {
        return a + b
    }
    fun some2(a: Int, b: Int): Int = a + b
    //single expression function, 결과가 나온다.. 타입 유추가 가능하다..
    //리턴 타입 생략 가능하다.. Unit 이 아니다..
    fun some3(a: Int, b: Int) = a + b

    println(some(10, 20))
    println(some2(10, 20))
    println(some3(10, 20))

    //overloading.....
    fun myFun(a: String){ }
    fun myFun(a: Int){ }
    fun myFun(a: Int, b: String){}

    myFun("hello")
    myFun(10)
    myFun(10, "hello")
}