package ch3.section6_abstract;

class Super1 { }

abstract class Super2 {
    //클래스가 추상형으로 선언되었고, 추상 함수가 없다.. 상관없다..
    //이런 클래스는 의미는 없다..
}

class Super3 {
    //추상 함수가 선언되어 있다면 그 클래스도 추상형으로 선언해야 한다..
//    abstract void method();//error...
}

abstract class Super4 {
//    abstract void method1(){}//error... 추상형으로 선언된 함수는 바디를 가질수 없다..
    abstract void method1();
    //클래스가 추상형이라고 하더라도 정상 함수 가능..
    void method2() {}
}

//추상 클래스 하위를 추상형으로 만들면.. 에러는 사라지지만.. 이 클래스는 객체 생성 불가..
abstract class Sub extends Super4 { }

class Sub2 extends Super4 {
    //상위의 추상 함수를 모두다 오버라이드...
    //함수 재정의임으로.. 자신에게 추상 함수가 상속되지 않는다..
    //나는 추상함수를 가지지 않게 된다.. 객체 생성해서 이용 가능해진다..
    @Override
    void method1() {

    }
}

abstract class Student {
    String name;
    int score;
    Student(String name, int score){
        this.name = name;
        this.score = score;
    }

    abstract void examTake();
    abstract void examSolve();
    abstract void examSubmit();

    void doExam(){
        //아래의 함수를 호출하려면.. 이 클래스를 작성하는 시점에..
        //함수가 선언되어 있어야 한다..
        //각 함수의 행위가 하위 클래스마다 로직이 다르다..
        examTake();
        examSolve();
        examSubmit();
    }
}
class HighStudent extends Student {
    HighStudent(String name, int score){
        super(name, score);
    }

    @Override
    void examTake() {
        System.out.println("High take...");
    }

    @Override
    void examSolve() {
        System.out.println("High solve...");
    }

    @Override
    void examSubmit() {
        System.out.println("High submit...");
    }
}

public class Exam1 {
    public static void main(String[] args) {
        Super1 obj1;
        Super2 obj2;//추상 클래스도 타입으로는 이용..
        obj1 = new Super1();
//        obj2 = new Super2();//error.... 추상 클래스는 생성 불가..
        //추상 클래스 그 자체가 사용될 수는 없다..

        Student student = new HighStudent("kim", 30);
        student.doExam();
    }
}
