package org.example.ch2.section2_variable_type.four

fun main() {
    //코틀린에서 자바의 최상위인 Object 사용 가능...
    //Object 클래스가 무언가 의미있는 데이터, 함수를 가지는 클래스가 아니기도 하고..
    //코틀린의 최상위 타입이 Any 가 있기 때문에 코틀린 코드에 Object 를 사용하는 경우는 없다.
    val data1: java.lang.Object = Object()
    //Object - Any 누가 상위인가?
    val data2: Any = data1//Object -> Any : 암시적 캐스팅
    val data3: Object = data2 as Object//Any -> Object : 명시적 캐스팅..
    //Any 가 Object 의 상위..

    fun a(): Int {
        return 10
    }
    //return 구문이 없다.. Unit..
    fun b(): Unit {

    }
    //함수의 리턴 타입이 명시되지 않으면 기본 Unit 타입..
    fun c(){

    }
    //Unit 은 예약어가 아니라 타입이다.. 변수의 타입으로도 사용이 가능하기는 하다.. 의미없지만..
    val data4: Unit = Unit
    
    //Nothing... 주로.. 함수의 리턴타입으로 사용되어.. 이 함수에서 의미있는 리턴이 있지는 않아..
    //를 명시적으로 표현하고자 할때...
    fun myFun(): Nothing {
        throw Exception()
    }
    fun myFun2(): Nothing {
        while (true){

        }
    }
    //타입이어서.. 변수 타입으로 사용이 가능하지만.. null 만 대입이 되어 의미가 없다..
    val data5: Nothing? = null
}

