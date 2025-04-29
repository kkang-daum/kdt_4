package org.example.ch3.section5_abstract.two

interface MyInterface1 {
    fun myFun1()
}
interface MyInterface2 {
    fun myFun2()
}
open class Super {
    fun mySuperFun(){}
}

class Sub: Super(), MyInterface1, MyInterface2 {
    override fun myFun1() {

    }

    override fun myFun2() {

    }
}

//클래스 선언 위치에 : 뒤에 상위 클래스(1개만), 인터페이스 나열(n개)..
//순서가 있지는 않다.. 상위 클래스를 보통 앞에 쓴다..
class Sub2: MyInterface1, Super(), MyInterface2 {
    override fun myFun1() {

    }

    override fun myFun2() {

    }
}

fun main() {
    val obj : Sub = Sub()
    val obj1: Super = Sub()
    obj1.mySuperFun()
    //interface 는 타입으로 활용 가능하다..
    val obj2: MyInterface1 = Sub()
    obj2.myFun1()
}

//interface 와 property.......................
interface MyInterface3 {
    //추상형으로 선언..
    //java 변형시에 getter/setter 가 추상형이 된다..
    var data1: Int

    //인터페이스의 프로퍼티에 값 직접 대입해서 초기화 시킬 수 없다..
    //값을 직접 대입하면 이 값을 가지는 변수가 자바 interface 에 선언되어야 하는데..
    //자바의 interface 는 static final 만 허용하지, 일반 변수 허용하지 않는다..
//    val data2: String = "kim"//error

    //get() 추가.. 더이상 추상형이 아니다..
    //get() 내에 field 사용할 수 없다.. field 는 자바의 변수를 지칭한다..
//    val data2: String
//        get() = field//error..

    //추상형이 아닐려면.. var 는 get, set 모두 선언해야 한다..
    //get 만 선언하면.. default set 이 추가되는데.. 그곳에 field 가 있어서..
//    var data3: String
//        get() = "kim"//error...

    val data2: String
        get() = "kim"

    var data3: String
        get() = "kim"
        set(value){

        }
}