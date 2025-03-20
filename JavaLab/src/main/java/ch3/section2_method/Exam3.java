package ch3.section2_method;

//가변 인수 테스트....

public class Exam3 {
    void method1(int arg1, int arg2){ }

    void method2(int... args){
        //instanceof - 객체의 타입 확인 연산자..
        //가변 인수는 함수내에서 배열이다..
        System.out.println(args instanceof int[]);//true
        for(int data : args){
            System.out.print(data + " ");
        }
        System.out.println();

    }

    //가변인수와 다른 인수를 같이 선언도 가능..
    void method3(String arg1, int... args){ }

    //아래는 모두 에러..
    //여러개, 중간 - 전달되는 데이터의 순서 문제가 있다..
    //가변인수는 하나의 함수내에서 한번만...
//    void method4(String... arg1, int... arg2) { }
    //가변인수는 다른 인수랑 같이 선언된다면.. 마지막 매개변수를..
//    void method5(int... args, String arg2) { }

    public static void main(String[] args) {
        Exam3 obj = new Exam3();
        obj.method1(10, 20);

        obj.method2();
        obj.method2(10);
        obj.method2(10, 20, 30, 40);

        obj.method3("hello", 10, 20);
    }
}
