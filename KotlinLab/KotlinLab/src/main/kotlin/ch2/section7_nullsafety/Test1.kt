package org.example.ch2.section7_nullsafety

var data1: String = "kim"//String, non-null
var data2: String? = "kim"//String, nullable

fun main() {
//    data1 = null//error...
    data2 = null


    //nullable 이 타입적으로 non-null 의 상위 타입이다..
    var data3: String? = data1//String -> String? (암시적 캐스팅)
    data3 = data2

    var data4: String = data1
//    data4 = data2//String? -> String, error... 명시적 캐스팅 해야한다..

    data2 = "kim"
    data4 = data2 as String //nullable -> non-null 명시적 캐스팅..
    data2 = null
    data4 = data2 as String//as 로 명시적 캐스팅을 하고 싶은데.. data2 가 null 이면 어떻게?
    //==>runtime error....

    //nullable 의 casting 은 null 일 수가 있어서.. as?
    //왼쪽 객체가 null 이 아니면 캐스팅 진행..
    //null 이면 캐스팅을 진행하지 않고.. 전체 null
    //as? 는 결과가 null 이 나올 수 있는 구문이다..
    val data5: String? =  data2 as? String
    //as? 의 결과를 = 로 대입되기는 하지만.. nullable 로 하면 안되는 경우..
    val data6: String = data2 as? String ?: ""


    //null safety operator.................
    class User {
        var name: String? = "kim"
    }
    val user: User? = User()
    println(user?.name?.length)

    //멤버 접근이 아니라.. 어떤 객체가 null 이 아닐때 실행 구문, null 일때 실행구문..
    user?.let {
        println("hello")
    } ?: {
        println("world")
    }
}