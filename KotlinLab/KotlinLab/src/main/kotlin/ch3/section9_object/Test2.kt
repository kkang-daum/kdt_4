package org.example.ch3.section9_object.two

class Outer {
    private var no = 10

    //클래스 선언과 동시에 객체 생성한 것이다..
    //익명 클래스이다..
    val myInner = object {
        var innerData = 0
        fun innerFun(){
            innerData++
            no++//ok...outer 멤버 이용 가능하다..
        }
    }
    fun outerFun(){
        //이름이 없다.. 객체가 타입이 없을 수는 없고..
        //Any 타입의 객체로 인지된다..
        //Any 에는 innerData 가 없다..
//        myInner.innerData++//error
//        myInner.innerFun()//error
    }
}

class Outer2 {
    private var no = 10

    //private 으로 선언하면.. outer 에서 익명 객체 멤버 참조가 가능해 진다..
    //컴파일러가.. 내부적으로 이름을 가지는 새로운 클래스를 만들고 그 클래스내에 포함시켜..
    //타입으로 표현되게 코드를 구성해 주기 때문...
    private val myInner = object {
        var innerData = 0
        fun innerFun(){
            innerData++
            no++
        }
    }
    fun outerFun(){
        myInner.innerData++//ok
        myInner.innerFun()//ok
    }
}