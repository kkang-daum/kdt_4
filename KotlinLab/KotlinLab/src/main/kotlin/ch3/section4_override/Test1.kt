package org.example.ch3.section4_override

open class Shape {
    //상위에서 명시적으로 override 가능하게 open 으로 명시해야 한다..
    open fun draw(){
        println("Shape draw....")
    }

    open var name: String = "aaa"
}

open class Rect: Shape(){
    //override 예약어로 명시적으로 선언되어야...
    //override 는 내부적으로 open 을 내장하고 있다...
    //override 받은 함수를 다시 그 하위에서 재정의하지 못하게 할때.. final 을 명시적으로
    override fun draw(){
        println("Rect draw....")
    }

    override var name: String = "bbb"
        set(value) {
            field = value.uppercase()
        }
}
class RoundRect: Rect() {
    override fun draw() {
        println("RoundRect draw.....")
    }
}

fun drawing(obj: Shape){
    //Shape 클래스의 draw() 함수만 보고 개발했다..
    obj.draw()
}

fun main() {
    drawing(Shape())
    drawing(Rect())
    drawing(RoundRect())
}