package org.example.ch3.section8_sealed

sealed class Shape(val x: Int, val y: Int){
    class Circle(x: Int, y: Int, val radius: Int): Shape(x, y)
}
class Rect(x: Int, y: Int, val width: Int, val height: Int): Shape(x, y)

fun main() {
    //일반 클래스와 다르게.. sealed 클래스는 직접 생성해서 이용 불가..
    //서브 클래스만 생성 가능..
//    val obj1 = Shape(10, 10)//error......
    val obj2: Shape = Shape.Circle(10, 10, 5)
    val obj3: Shape = Rect(10, 10, 20, 20)
}

//추상 클래스도 직접 생성이 안된다.. 서브 클래스의 객체만 생성가능하다. ==>sealed 와 동일
//추상 클래스의 생성자는 public 이 된다.. 아무곳에서나.. 하위 클래스 만들 수 있다는 의미..
abstract class AbstractClass public constructor() {

}
//public 생성자를 추가할 수 없다.. abstract 클래스와는 차이가 있다..
//동일 파일, 동일 패키지내에서만 sub 를 만들 수 있다..
//sealed class 의 생성자는 private 으로 고정되어.. 원래.. 동일 파일 내에서만 서브 만들수 있는데..
//컴파일러 기법으로.. 동일 패키지까지 확장되었다..
sealed class SealedClass private constructor() {

}