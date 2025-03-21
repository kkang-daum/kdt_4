package ch3.section5_inherited.test9;

//oop 의 캐스팅은 상하위 관계에서만 논할 수 있다..
//전혀 관련없는 클래스간의 캐스팅 안된다..
//형제관계에서도 안된다..

class Super {}
class Sub extends Super{}

public class Exam9 {
    public static void main(String[] args) {
        Super obj1 = new Super();
        Sub obj2 = new Sub();

        //실제 생성되는 객체는 Sub, 타입으로 Super
        //Super 에 선언된 멤버만 사용이 가능..
        //암시적 캐스팅.. 하 -> 상
        Super obj3 = new Sub();
        obj3 = obj2; //하 -> 상

//        Sub obj4 = obj3;//error... 상 -> 하
        Sub obj4 = (Sub)obj3;//ok... 상 -> 하....명시적 캐스팅

        //Exception in thread "main" java.lang.ClassCastException
        Sub obj5 = (Sub)obj1;//상 -> 하, compile ok, runtime error...
        //하 -> 상 -> 하 인경우에 한해서만 상위가 하위로 캐스팅
    }
}
