package org.example.ch5.section4_annotation

import java.lang.reflect.Method

//단위테스트...
//애플리케이션 테스트 중요하다..
//개발자에게 중요한 테스트는 내가 작성한 코드가 정상적으로 실행된다는 테스트..
//개발 클래스 하나에 테스트 코드 하나
//User(B/L) - UserTest(Test)
//단위테스트..
//자바의 유명한 단위 테스트 툴이 JUnit

//Junit 을 가정해보자..
//junit 에서 annotation 을 선언하고.. 개발자들이 이 annotation 을 추가해주면 자동으로 테스트 해준다
annotation class TestBegin //테스트 시작 전에 실행될 함수에 추가하라..

annotation class TestEnd //테스트가 마무리 된다음에 호출할 함수에 추가하라..

//각 테스트가 필요한 함수에 추가하라..
annotation class TestEqualsInt(val expect: Int, val desc: String)


//junit 을 이용하는 현업 개발자 입장의 코드..................................

//정상적으로 업무가 구현된 멋진 클래스..
class MyClass {
    fun some(arg1: Int, arg2: Int): Int {
        return arg1 + arg2
    }
}
//업무 클래스를 단위 테스트 하기 위한 클래스..
class Test {
    val obj = MyClass()

    @TestBegin
    fun begin(){//함수명이 중요하지 않다.. 어노테이션이 추가되었다는 것이 중요하다..
        println("begin.....")
    }

    @TestEnd
    fun end(){
        println("end.....")
    }

    @TestEqualsInt(expect = 10, desc = "some() equals test")
    fun some(): Int {
        return obj.some(5, 5)
    }
}

//junit 에서 실행되는 코드라는 가정.............
fun main() {
    val obj = Test()

    val methods = Test::class.java.methods

    val beginMethodList = mutableListOf<Method>()
    val endMethodList = mutableListOf<Method>()
    val assetMethodList = mutableListOf<Method>()

    for(method in methods){
        if(method.isAnnotationPresent(TestBegin::class.java)){
            beginMethodList.add(method)
        }
        if(method.isAnnotationPresent(TestEnd::class.java)){
            endMethodList.add(method)
        }
        if(method.isAnnotationPresent(TestEqualsInt::class.java)){
            assetMethodList.add(method)
        }
    }

    for(method in beginMethodList){
        //TestBegin 어노테이션이 추가된 그 함수 호출..
        //함수를 호출하기 위해서는 매개변수가 먼저 준비되어야 한다.. 없어도 준비한다..
        val arguments = arrayOfNulls<Any>(0)
        method.invoke(obj, *arguments)
    }

    for(method in assetMethodList){
        val annotation = method.getAnnotation(TestEqualsInt::class.java)
        val expect = annotation.expect
        val desc = annotation.desc

        println("test.... $desc")
        val arguments = arrayOfNulls<Any>(0)
        val result = method.invoke(obj, *arguments) as Int
        if(result == expect){
            println("result ok... expect: $expect, result : $result")
        }
    }

    for(method in endMethodList){
        val arguments = arrayOfNulls<Any>(0)
        method.invoke(obj, *arguments)
    }
}
