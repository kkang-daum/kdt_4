package org.example.ch2.section2_variable_type.five

fun main() {
    //int <-> double
    val data1 = 10
//    val data2: Double = data1//error...
//    val data2: Double = data1 as Int//error...

    //Double 과 Int 는 클래스이다.. 상하위 관계의 클래스가 아니다..
    //캐스팅 안된다..
    //각 클래스의 함수를 이용해야 한다..
    val data2: Double = data1.toDouble()
    val data3: Int = data2.toInt()

    //Int, Double 등 숫자타입으로 표현되는 클래스의 상위는 Number
    val data4: Number = data1//암시적 캐스팅..
    val data5: Int = data4 as Int //명시적 캐스팅...


    //Int <-> String
    val data6: String = data1.toString()
    val data7: Int = data6.toInt()
}
