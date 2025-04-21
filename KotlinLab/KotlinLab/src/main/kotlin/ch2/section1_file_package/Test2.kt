package org.example.ch2.section1_file_package

//다른 패키지의 변수, 함수를 사용하겠다면.. 변수, 함수를 import
import org.example.ch2.section1_file_package.two.twoData
import org.example.ch2.section1_file_package.two.twoFun
import java.util.Date
import java.sql.Date as ADate

fun main() {
    //동일 패키지내에 선언된 다른 코틀린 파일의 top level 구성요소는
    //동일 패키지내에서 global(import 없이 바로 사용 가능)
    println(oneData)
    oneFun()

    println(twoData)
    twoFun()

    //아래처럼 Two.kt 가 자바로 변형시점에 TwoKt.class 로 만들어짐으로..
    //아래처럼 사용.. 불가하다..(kotlin 코드에서는)
    //자바코드에서 이용하려면 아래처럼 이용해야 한다..
//    TwoKt.twoData++;


    val date = Date()
    //하나의 kt 파일내에서.. 다른 패키지의 Date 클래스를 또 사용해야 한다면..
    val date2 = java.sql.Date(System.currentTimeMillis())
    val date3 = ADate(System.currentTimeMillis())
}