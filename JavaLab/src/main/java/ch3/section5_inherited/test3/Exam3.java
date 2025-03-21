package ch3.section5_inherited.test3;

import ch3.section5_inherited.test3.sub.Super;
import ch3.section5_inherited.test3.sub.Two;

public class Exam3 extends Super {
    public static void main(String[] args) {
        //동일 패키지의 클래스 이용..
        One one = new One();
        one.publicData++;
        one.protectedData++;
        one.defaultData++;
//        one.privateData++;//error..

        //다른 패키지...
        Two two = new Two();
        two.publicData++;
//        two.protectedData++;//error
//        two.defaultData++;//error
//        two.privateData++;//error

        //상위 멤버, 패키지가 다른..
        Exam3 obj = new Exam3();
        obj.publicSuperData++;
        obj.protectedSuperData++;//패키지가 다르지만.. 상속관계의 하위라면 이용가능
//        obj.defaultSuperData++;//error
//        obj.privateSuperData++;//error
    }
}

//접근제한자는 모든곳에 모든 제한자가 추가되지는 않는다..
//클래스 선언에는 public 과 default 만 허용..
class A {}//ok
//private class A2{}//error
//protected class A3{}//error

class B {
    //변수, 함수에는 모든 접근제한자..
    //static 으로 선언된 멤버도 모든 접근제한자..

    B(){}
    public B(int a){}
    protected B(String a){}
    private B(double a){}//이 클래스 내부에서만 객체생성 가능하다..

    void method(){
        //접근제한자와 로컬 변수는 관련이 없다..
        //아무것도 추가할 수 없다..
        //로컬 변수는 그 함수내에서만.. 그 함수가 실행되는 도중에 이용.
//        protected int a = 0;

    }
}

