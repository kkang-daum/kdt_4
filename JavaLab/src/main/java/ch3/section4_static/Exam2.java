package ch3.section4_static;

//static 과 this...

public class Exam2 {
    int objVar = 0;
    static int staticVar = 0;

    void objMethod(){
        //object member 의 함수에서는 object member, static member 모두 이용가능
        objVar++;
        staticVar++;
        staticMethod();
    }
    static void staticMethod(){
        //static 함수에서 object 멤버 이용이 불가능하다..
        //이용할려면 이곳에서 객체생성해서.. 메모리 할당을 한다음 이용해야 한다.
//        objVar++;//error
        staticVar++;
//        objMethod();//error
    }

    public static void main(String[] args) {
        staticVar++;

        Exam2 obj = new Exam2();
        obj.objVar++;
    }
}
