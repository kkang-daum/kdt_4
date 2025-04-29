package org.example.ch3.section5_abstract

abstract class Super {
    val data1: Int = 10
    abstract val data2: Int

    fun myFun1(){ }
    abstract fun myFun2()
}

class Sub: Super() {
    override val data2: Int = 10
    override fun myFun2() {

    }
}

fun main() {
//    val obj1 = Super()//error. 추상 클래스는 객체 생성할 수 없다..
    val obj2 = Sub()
}