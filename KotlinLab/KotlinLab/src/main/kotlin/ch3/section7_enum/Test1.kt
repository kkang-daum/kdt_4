package org.example.ch3.section7_enum

//단순 enum class 선언....
enum class Direction {
    NORTH, SOUTH, WEST, EAST
}

//원한다면.. enum 상수에 개발자 임의 데이터 추가...
//먼저 enum class 생성자에.. 멤버 변수 선언.. enum 상수에서 상위 생성자 호출하면서 데이터 추가
enum class Direction2(val no: Int, val email: String) {
    //enum 상수는 enum 클래스의 하위 타입이다.. 상위 생성자 연결 관계 맞추어야 한다..
    NORTH(10, "a@a.com"), SOUTH(20, "b@b.com")
}

//enum 상수에 함수까지 추가하고 싶은 경우...
enum class Direction3 {
    NORTH {
        //클래스 BODY
        //임의 변수 선언, 임의 함수 선언 얼마든지 가능하다..
        //단 타입적으로 표현이 안되어서.. 이 클래스 내에서만 이용이 가능하지. 외부에서
        //절대 이용할 수 없다..
        val data1: Int = 10
        fun some1(){}

        override val data3: Int = 30
        override fun some3() {
            println("north... some3")
        }
    },
    SOUTH {
        val no: Int = 20
        fun some2(){}

        override val data3: Int = 40
        override fun some3() {
            println("south... some3")
        }
    };
    //정상 프로퍼티, 정상 함수는.. enum 상수와 관련없고..
    val data2 = 10
    fun some(){}

    abstract val data3: Int
    abstract fun some3()

}

fun main() {
    //enum 클래스 타입으로 변수가 선언되었다. 이 변수에는 enum 상수중 하나만 대입이 가능하다
    //어떤 타입이 선언되면.. 그곳에 대입될 수 있는 객체는 그 클래스의 객체와 서브 클래스 객체만..
    //enum 상수가 결국 enum 클래스의 하위 클래스 객체이기 때문에 가능..
    val data: Direction = Direction.NORTH
    //자동으로 유지되는 property
    println("${data.name}, ${data.ordinal}")//NORTH, 0


    val data2: Direction2 = Direction2.SOUTH
    println("${data2.no}, ${data2.email}")//20, b@b.com


    //enum 상수, 하위 타입의 객체가 대입이 되었지만.. 타입이 중요하다..
    //상위 타입으로 선언된거다..
    val data3: Direction3 = Direction3.NORTH
    println("${data3.data3}")//30
    data3.some3()//north... some3

}

open class Super {
    fun a(){}
}
class Sub: Super(){
    fun b(){}
}

fun test(){
    val obj: Super = Sub()
    obj.a()
}