package ch3.section1_oop;

class Student {
    String name;
    int score;

    void printInfo(){
        System.out.println(name + "의 점수 : " + score);
    }
}

public class Exam2 {
    public static void main(String[] args) {
        //객체 생성..
        Student s1 = new Student();
        s1.name = "kim";
        s1.score = 100;

        Student s2 = new Student();
        s2.name = "lee";
        s2.score = 90;

        //두개의 객체가 생성이 되었다.. 따로 메모리가 할당된다..
        //즉 하나의 클래스로 만들었지만 각기 메모리를 가지고 있어, 각각의
        //데이터를 자신 객체의 메모리에 유지..
        s1.printInfo();
        s2.printInfo();
        //kim의 점수 : 100
        //lee의 점수 : 90

        //객체, reference variable... 참조 변수.. 주소값이 들어간 변수..
        System.out.println(s1 == s2);//false

        //객체를 선언했지만.. new 로 새로 생성한 것이 아니라..
        //기존에 있던 객체를 그대로 대입..
        //기존 메모리 주소를 복사한 경우이다..
        //두 객체는 동일한 메모리를 지칭하게 된다..
        Student s3 = s1;
        System.out.println(s1.name +","+s3.name);//kim,kim
        s1.name = "park";
        System.out.println(s1.name +","+s3.name);//park,park
        s3.name = "hong";
        System.out.println(s1.name +","+s3.name);//hong,hong

        System.out.println(s1 == s3);//true
    }
}
