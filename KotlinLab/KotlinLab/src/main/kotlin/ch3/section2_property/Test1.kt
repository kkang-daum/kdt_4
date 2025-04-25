package org.example.ch3.section2_property

import ch3.TextView

fun main() {
    //변수를 직접 노출 시키고, 변수를 직접 이용한 것처럼 보이지만..
    //실제는 setter/getter 만 노출이 되고.. setter/getter 를 이용한 코드이다..
    class User1 {
        var name: String = "kim"
        val age: Int = 20
    }
    val user1 = User1()
    user1.name = "lee"
    println("${user1.name}, ${user1.age}")

    //위의 코드는 아래처럼 작성된 것과 동일하다..
    class User2 {
        //field.. get, set 내에서만 예약어.. 변수 값 자체를 지칭하는 예약어..
        //get() : 매개변수 없어야 하고.. property 타입과 동일 타입의 데이터가 리턴.
        //set() : 매개변수 하나, property 타입과 동일한 타입의 매개변수.. 리턴은 Unit
        var name: String = "kim"
            get() = field
            set(value) {
                field = value
            }

        val age: Int = 20
            get() = field
    }
    val user2 = User2()
    user2.name = "lee"
    println("${user2.name}, ${user2.age}")

    //custom accessor.......................
    class User3 {
        var name: String = "kim"
            set(value) {
                field = "Hello "+value
            }
            get() = field.uppercase()

        var age: Int = 20
            set(value) {
                if(value > 0)
                    field = value
                else
                    field = 0
            }
    }
    val user3 = User3()
    user3.name = "lee"
    println("${user3.name}, ${user3.age}")
}

//custom accessor 를 추가할 때... field 를 사용하지 않으면..
//자바로 변형될때.. 변수가 선언되지 않는다..
//field 를 사용하지 않으면.. property 선언에서 초기값을 줄 수 없다..
//val data1: String = "aaa"//error
val data1: String
    get() = "lee"

//var....
//var data2 = "kim"//error...
var data2
    get() = "lee"
    set(value) {
        data2 = value
    }

fun someFun() {
    println(data1)

    val textView = TextView()
    textView.setText("hello")
    textView.text = "lee"
    textView.text

    textView.textColor = 0
    textView.textColor

    //getXXX, setXXX로 선언된 함수를 변수(property) 로 이용하는 것인데..
    //getXXX 는 매개변수가 없어야 하고..
    //setXXX 는 매개변수가 하나이어야 한다..
    //그렇지 않으면 코틀린에서도 일반 함수이다..
    textView.getTextSize(0)
    textView.setTextSize(0, 0)

}