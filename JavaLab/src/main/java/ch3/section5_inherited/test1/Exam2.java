package ch3.section5_inherited.test1;

//클래스를 선언하면서 명시적으로 extends에 의해 상위 클래스를 지정하지 않으면.
//자동으로 Object 클래스를 상속받게 되어 있다..
//Super1 은 Object 의 하위 클래스가 된다..
//Object 는 자바 모든 클래스의 최상위 .....
//자바의 모든 클래스는 Object 의 하위...
class Super1 {}

class Sub1 extends Super1 {}


class Super2 {
    //개발자가 생성자를 추가하기 시작하면.. 컴파일러에 의해 
    //default 생성자가 추가가 안된다..
    //이 클래스는 매개변수 하나의 생성자만 있다..
    Super2(int a) {}
}
class Sub2 extends Super2 {
    //생성자를 명시적으로 선언하지 않으면 컴파일러가 default 생성자 추가
    //모든 하위 생성자는 그 생성자로 객체가 생성될때 무조건.. 상위 생성자가
    //호출이 되어야 한다..
    //그래서 실제 컴파일러에 의해 추가된 생성자를 유추하면...
//    Sub2(){
//        super();//상위에 default 생성자 없다.. 생성자 호출을 맞추지 못해서.
//    }
    
    //상위에 default 생성자가 없으면.. 개발자가 생성자를 직접 정의해서
    //상위 생성자 호출 구문을 맞출수 밖에 없다..
    Sub2(){
        super(10);
    }
}

class Super3 {
    Super3(int a){}
    Super3(String a, int b){}
}
class Sub3 extends Super3 {
    Sub3(int a){
        super(10);
        //super() 로 상위 생성자 호출 구문은 생성자 내에서 가장 첫줄, 한번만
//        super("hello", 20);//error...
    }
    Sub3(String a, int b){
        //이곳에.. 명시적으로.. 개발자가 super() 를 추가하지 않으면..
        //컴파일러가 가장 첫줄에 super() 추가해준다..
        //자신의 다른 생성자를 호출하고 싶다면? 상위 생성자는 호출되어야 하는데?
        //원한다면 자신의 생성자 호출 가능..
        this(b);
//        super(b);
        //어떻게 생성자를 연결하든.. 객체 생성시점에 상위 생성자가 호출되게
        //만들면 된다..
    }
}

public class Exam2 {
}
