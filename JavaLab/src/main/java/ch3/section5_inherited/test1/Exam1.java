package ch3.section5_inherited.test1;

//모든 학생의 공통 코드...변수, 함수..
class Student {

    String name;
    int score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

//하위 클래스..
class HighStudent extends Student {
    int classNumber;

    public int getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(int classNumber) {
        this.classNumber = classNumber;
    }
}
class UniversityStudent extends Student {
    String major;

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}

public class Exam1 {
    public static void main(String[] args) {
        UniversityStudent obj1 = new UniversityStudent();
        obj1.name = "홍길동";
        obj1.setScore(40);
        obj1.major = "컴퓨터공학";

        HighStudent obj2 = new HighStudent();
        obj2.name = "김길동";
        obj2.setScore(60);
        obj2.classNumber = 2;
    }
}
