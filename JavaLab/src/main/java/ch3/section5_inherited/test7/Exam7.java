package ch3.section5_inherited.test7;

//override test.................

//상위..
class Student {
    String name;
    int score;
    void printInfo(){
        System.out.println(name+"의 점수는 "+score);
    }

    void method(){}
}
class UniversityStudent extends Student {
    String major;
    //override 로 상위 함수 상속되지 않게 하고.. 동일 행동을 자신이 재정의..

    @Override
    void printInfo() {
        //super... 가 꼭 들어가야 하는 것은 아니고..
        //동일 이름의 함수가 상위에도 있고.. 자신에게도 있고..
        //호출하면 자신꺼 이용..
        //경우에 따라 자신의 함수가 호출되면서.. 상위의 함수까지 호출하고 싶다면..
        //이름이 동일하니까.. 명시적으로 상위임을 밝혀야 한다..
        //그때 super. 으로...
        super.printInfo();
        System.out.println(name+", "+major+","+score);
    }

//    void method(){}//ok....

    void method(int a){}//ok...매개변수를 다르게 하면.. 상위 함수 상속된다..
    //현 클래스내에 동일 이름의 함수가 두개가 되는 것이다. 오버로딩이다..

//    public void method(){}//ok... 상위의 접근제한자 교체 가능..
//    private void method(){}//error... 오버라이드 하면서 접근제한의 범위를 넓힐수는
    //있지만 줄일 수는 없다..

//    int method(){ return 0;}//error.. 리턴 타입 바꾸면 안된다..
}
public class Exam7 {
    public static void main(String[] args) {
        UniversityStudent obj = new UniversityStudent();
        obj.name = "kim";
        obj.score = 100;
        obj.major = "컴퓨터공학";
        obj.printInfo();


    }
}
