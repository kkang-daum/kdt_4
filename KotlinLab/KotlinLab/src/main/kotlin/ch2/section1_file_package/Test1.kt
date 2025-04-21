package org.example.ch2.section1_file_package

//kotlin 은 top level 에 클래스 이외에 변수, 함수 선언 가능..
//클래스 멤버 변수, 함수가 아니라면.. top level 에 선언..
//top level 에 선언된 변수, 함수는.. 동일 패키지내에서 global 처럼 이용이 된다...
var sum = 0

fun calSum(){
    for (i in 1..10 ){
        sum += i
    }
}

//클래스 선언 가능하고.. 객체의 멤버변수, 함수라면.. 클래스 내에 선언..
class User {
    val name = "kim"
    fun sayHello(){
        println("Hello "+name)
    }
}

//kotlin 의 entry point....
fun main() {
    //kotlin 은 라인의 끝을 표현하는 ; 을 강제하지 않는다..
    println("Hello Kotlin")
    calSum()
    //kotlin 에서 객체 생성시에. new 연산자는 제공하지 않는다..
    User().sayHello()
}

//kotlin 코드가.. java class 로 컴파일이 되어서 JVM 에 의해 실행이 되었다...
//class User { ~~~~ } 부분은 컴파일에 의해 User.class 파일이 만들어져서 이용된 것으로 예상...
//sum, calSum(), main() 함수 부분이 자바로 컴파일 되었을때.. 클래스로 묶지도 않았는데..
//어떻게 build error 및 runtime error 가 발생하지 않는 것인가????
//jvm 으로 돌릴려면 무조건 클래스로 묶여야 가능한데..????

//kotlin 코드내에 특정 클래스로 묶이지 않은 부분은..
//java 로 컴파일 할때... 파일명Kt.java 로 묶어서 컴파일 한다.. 파일명Kt.java 내에 static member
//로 자동 추가된다..

//코틀린 코드내에서는 자바로 변형시 top level 에 선언된 변수, 함수가 XXXKt.class 로 묶인다는 것을
//인지하지 않고.. 독립적으로 이용된다고 생각하면서 작성해도 된다..
//내부적으로 자바로 변형될때 어떻게 변형되는지 원론을 이해해야 할때, 혹은 자바코드에서
//코틀린으로 개발한 코드를 이용할때는 알아야 한다..



