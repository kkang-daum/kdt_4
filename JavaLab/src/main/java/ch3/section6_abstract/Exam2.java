package ch3.section6_abstract;

interface ITest1 {
    //자동으로.. public static final 추가...
    int COUNT = 1;
    //아래처럼 선언한 것과 동일하다..
    public static final int MAX_SIZE = 10;

    //접근제한자.. public 으로 고정된다..
//    private int a = 0;//error
//    protected int b = 10;//error

    void method1();
    //위의 함수는 아래처럼 선언된 것과 동일하다..
    //public abstract 추가
    public abstract void method2();

    //구현체를 가지는 함수를 추가하고 싶다면.. default, static, private 중 하나
    //구현한 객체
    default void method3(){
        System.out.println("default function");
        method1();
    }
}

public class Exam2 {
}
