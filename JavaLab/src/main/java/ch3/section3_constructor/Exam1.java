package ch3.section3_constructor;

class Student {
    String name;
    int score;
    boolean isPassed;
    String major;

    //개발자가 명시적으로 생성자를 추가하지 않으면 컴파일러에 의해 default 생성자
    //추가, default constructor - 매개변수를 가지지 않는..
    //마치 아래처럼 선언된 것과 동일하다..
//    Student(){}

    //학생 객체를 만들때.. 나머지 데이터는 이후에 발생한다고 하더라도..
    //이름이 없는 학생은 존재할 수 없다.
    //객체를 생성하면서 필수 데이터를 매개변수로 받아들여서.. 생성과 동시에..
    //초기화 되게 하고자 한다..
    Student(String argName) {
        name = argName;
    }
    //생성자 여러개 준비할 수 있다..
    //다른 멤버 변수도 필요하다면.. 생성하면서 초기화 되게 하고 싶다..
//    Student(String argName, int argScore, boolean argPassed, String argMajor){
//        name = argName;
//        score = argScore;
//        isPassed = argPassed;
//        major = argMajor;
//    }
    //생성자를 여러개 선언하다보면.. 생성자내의 코드가 중복되는 경우가 많다..
    //일반 함수가 아니다 보니.. 생성되면서 코드에 의해 실행되는 부분이 생성과 동시에 최초에 한번인
    //코드다 보니.. 대부분 중복..
    //name 에 데이터 설정하는 부분이 중복된다는 가정..
    //name 데이터 초기화는 다른 생성자 호출로..
//    Student(String argName, int argScore, boolean argPassed, String argMajor){
//        this(argName);
//        score = argScore;
//        isPassed = argPassed;
//        major = argMajor;
//        //다른 생성자 호출 구문은 첫줄에 한번만..

    /// /        this(argName);
//    }
    //위 생성자를 보면... 매개변수의 데이터..멤버 변수와 의미상으로 동일한 데이터이다..
    //생성자 내에 작성되어서 local variable 임으로..
    //멤버 변수와 로컬 변수의 이름을 동일하게 하는 것이 권장이다..
    Student(String name, int score, boolean isPassed, String major) {
        //함수 내에서 멤버 변수와 로컬 변수를 모두 이용 가능
        //이름이 동일하다면 로컬 변수가 지칭된다..
        //this.멤버 로 정확하게 멤버 변수임을 지정..
        this(name);
        this.score = score;
        this.isPassed = isPassed;
        this.major = major;
    }
}

public class Exam1 {
    public static void main(String[] args) {
        //객체 생성 구문..
        //객체 생성을 위한 생성자 호출 구문..
        Student obj = new Student("kim");
        Student obj2 = new Student("lee", 100, true, "java");
        System.out.println(obj.name + "," + obj2.name + "," + obj2.score);//kim,lee,100
    }
}















