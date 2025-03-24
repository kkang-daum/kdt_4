package ch3.section7_inner_enum;

class OuterClass {
    int outerData = 10;
    //private 로 선언된 멤버다.. 자신 내부에서 밖에 못쓴다..
    private int outerData2 = 20;

    //inner class 로 선언되기는 했지만 컴파일 시키면.. 독립 class 가 나온다
    //이 클래스에서 어떻게 outer 의 private member 접근이 되는가?
    //컴파일러는 개발자가 작성한 코드만 추가하지 않는다..
    //private 멤버를 inner 에 선언한 클래스에서 이용할 수 있게...
    //public 함수를 만들어주고.. 그함수를 이용해서.. private 을 이용하는
    //코드로 바꾸어 준다..
    class InnerClass {
        int innerData = 20;
        void innerFun(){
            innerData++;
            outerData++;
            outerData2++;
        }
    }

    static class StaticInnerClass {
        void innerFun(){}
    }
}

class Super {
    void superFun(){}
}
interface MyInterface {
    void interfaceFun();
}

public class Exam1 {

    static void some(MyInterface obj){
        obj.interfaceFun();
    }

    public static void main(String[] args) {
        System.out.println("hello");

        OuterClass outer = new OuterClass();
        OuterClass.InnerClass obj = outer.new InnerClass();
        obj.innerData++;

        OuterClass.StaticInnerClass obj2 = new OuterClass.StaticInnerClass();

        //익명 클래스는 선언과 동시에 객체 생성해야 한다..
        Super obj3 = new Super(){
            @Override
            void superFun() {
                super.superFun();
            }
        };

        MyInterface obj4 = new MyInterface(){
            @Override
            public void interfaceFun() {

            }
        };

        some(new MyInterface() {
            @Override
            public void interfaceFun() {

            }
        });

    }
}
