package ch1.section3;//자바 파일이 있는 물리적인 폴더 위치와 동일하게.
//package aaa;//error.... package 는 가장 첫줄에.. 한번만 선언가능..

//다른 패키지에 선언된 클래스를 이용하겠다면...
//import 구문으로.. 컴파일러에게 어떤 위치의 어느 클래스를 사용하는 것인지..
//알려줘야 한다..
//동일 패키지의 다른 자바 클래스를 사용한다면.. import 없이 사용..
//import ch1.section3.sub.Exam1_2;//ok...
import ch1.section3.sub.*;//ok
//import 는 여러줄 가능..
//java 로 시작하는 패키지는 jdk 에서 제공하는 클래스..
import java.util.Date;
import java.io.Writer;


//자바 파일 구성 요소 테스트......................

//개발을 하면서.. 변수, 함수, 클래스 선언..
//변수 함수는 top level 에 선언될 수 없다..
//int a = 10;
//void sayHello() {}

public class Exam2 {
    Exam1 obj;
    Exam1_2 obj2;

    //top level 구성요소는 클래스만 허용한다..
    //==>모든 변수, 함수는 클래스내에 선언되어야 한다..
    //클래스의 멤버 변수, 함수..
    int a = 10;
    void sayHello() {}
}
