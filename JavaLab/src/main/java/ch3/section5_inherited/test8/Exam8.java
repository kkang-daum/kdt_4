package ch3.section5_inherited.test8;

//final test... 클래스, 변수, 함수 선언위치에.. 추가되는 예약어..

//이 클래스는 확장시키지마.. 상속받아 서브 클래스 만들지마...
final class Super {
    //변수 앞에 선언.. 상수 변수..
    final int a = 0;
    final int b;
    Super(int a, int b){
        //final 로 변수를 선언과 동시에 값을 주지 않고 생성자에서 초기값을
        //줄수는 있다..
        this.b = b;
    }

    //final 은 생성자는 추가 불가..
//    final Super(){}

    //override 금지.. 내가 준비한 알고리즘으로만 써.. 절대 재정의하지마..
    final void method(){}
}

//class Sub extends Super {
//    Sub(){
//        super(10, 20);
//    }
//
////    @Override
////    void method() {
////        super.method();
////    }
//}

public class Exam8 {
}
