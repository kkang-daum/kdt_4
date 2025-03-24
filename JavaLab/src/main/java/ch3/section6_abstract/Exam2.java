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
    //구현한 객체에 그대로 상속개념으로.. 이용..
    default void method3(){
        System.out.println("default function");
        method1();
        method5();
    }
    //클래스의 static 처럼 이름.함수명..
    static void method4(){

    }
    private void method5(){}
}

interface ITest2{
    void iTest2();
}
interface ITest3 {
    void iTest3();
}
//인터페이스를 선언하면서 다른 인터페이스를 상속받아 선언 가능하다..
//인터페이스는 다중 상속이 가능하다..
interface ITest4 extends ITest2, ITest3 {
    void iTest4();
}

abstract class AbstractClass {
    abstract void some1();
}

//--------------------------------------
//클래스 선언하면서 여러개의 인터페이스 구현 가능..
class Sub1 extends AbstractClass implements ITest1, ITest4 {
    @Override
    void some1() {

    }

    @Override
    public void method1() {

    }

    @Override
    public void method2() {

    }
    //interface 에 선언된 default 로 선언된 함수를 구현 클래스내에서
    //오버라이드로 재선언 가능하다..
    @Override
    public void method3() {
        ITest1.super.method3();
    }

    @Override
    public void iTest2() {

    }

    @Override
    public void iTest3() {

    }

    @Override
    public void iTest4() {

    }
}

public class Exam2 {
    public static void main(String[] args) {
        Sub1 obj1 = new Sub1();

        //AbstractClass 타입으로 객체를 선언했다면..AbstractClass 에
        //선언된 멤버만..
        AbstractClass obj2 = new Sub1();

        //인터페이스도 타입으로 이용가능..
        ITest1 obj3 = new Sub1();

        ITest4 obj4 = new Sub1();

        Object obj5 = new Sub1();
        
    }
}
