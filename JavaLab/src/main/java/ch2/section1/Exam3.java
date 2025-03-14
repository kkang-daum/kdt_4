package ch2.section1;

//type test...............

public class Exam3 {
    public static void main(String[] args) {
        boolean isPressed = true;
        //char 데이터는 문자 하나.. 꼭 싱글쿼트로 묶어서..
        char grade = 'A';
        //표현할 수 있는 정수의 범위가 -128 ~ 127
//        byte byte1 = 128;//errro
        byte byte1 = 127;

        //자바 정수의 기본 타입은 int 타입..
        short short1 = 17;
        int int1 = 120;
        long long1 = 10L;//long 타입의 데이터라는 것을 명시적으로 표현사기 위해서
        //데이터 뒤에 L 을 추가하낟..

        //자바에서 실수의 기본 타입은 double
//        float float1 = 10.20;//error...
        float float1 = 10.0f;//f 로 float 타입의 데이터임을 명시적으로..
        double double1 = 10.0;

        //어떤 언어에서도 문자열을 표현하기 위한 기초 타입을 제공하지는 못한다..
        //문자열은 문자(char) 의 배열로 표현이 기본이다..
        //개발자가 코드에서 문자열을 char 배열로 핸들링하는건 너무 공수가 많이 든다.
        //이 문자열 처리를 마치 단일 데이터인 것처럼 도와주기 위한 클래스를 제공한다..
        //문자열은 그 클래스 타입의 객체로 표현한다..
        //String 클래스, 자바에서 제공하는...
        //문자열은 꼭 더블 쿼트..로 묶여야..
        String str = "hello world";
    }
}
