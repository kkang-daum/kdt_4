package org.example.ch5.section3_reflection

import kotlin.reflect.KFunction

//이 함수는 내가 만들었다.. 이 함수를 호출하는 곳은.. 이함수를 전혀 모르는 곳이다..
fun myFun(no: Int, name: String): Boolean {
    return true
}

//reflection 기법으로.. 넘어온 함수 호출.. 공통....
fun reflectionFun(argFun: KFunction<*>){
    print("${argFun.name}")
    //매개변수 정보...
    val parameters = argFun.parameters
    print("(")
    for(parameter in parameters){
        print("${parameter.name}: ${parameter.type}")
        if(parameter.index < parameters.size - 1)
            print(",")
    }
    print("): ")
    println("${argFun.returnType}")
}

fun main() {
    reflectionFun(::myFun)
}