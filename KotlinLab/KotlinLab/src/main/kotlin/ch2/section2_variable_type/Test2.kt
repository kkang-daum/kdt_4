package org.example.ch2.section2_variable_type.two

import kotlin.random.Random

//val valData = 10

//java 의 변수는 field(값 자체를 지칭)
//kotlin 의 변수는 property(값과.. 값 변경 및 획득을 위한 getter/setter 함수가 내장된 변수)
//기본 getter/setter 가 내장되지만, 개발자가 원한다면 getter/setter 를 customizing 가능..
//custom accessor 라고 부른다..
val valData: Int
    get() {//getter...
        return Random.nextInt(0, 100)
    }

//상수변수는 const 예약어로, 초기값 변경 불가능하며.. 항상 동일 값으로만 이용..
const val myConst = 10
//const var myConst2 = 10//error... const 로 선언하면 val 만 가능..

class MyClass {
    //클래스 멤버 변수를 const 로 선언할 수 없다..
    //자바와 다르게.. top level 에 변수 선언이 가능하다..
    //클래스 멤버 변수는.. 객체 생성 시점에.. 매번 메모리 할당되어.. 서로 다른 값을 유지하고자 하는 의도
    //상수 변수는.. 항상 동일값.. 컴파일 타임에..
//    const val myConst3: Int = 10
}

fun some(){
    //지역 변수도.. const 불가...
    //지역변수는 property 가 아니라.. field 이다.. val 로 충분하다..
//    const val myConst4 = 10
}

fun main() {
//    valData = 20
    println(valData)
    println(valData)
}