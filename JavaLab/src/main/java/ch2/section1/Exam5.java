package ch2.section1;

public class Exam5 {
    public static void main(String[] args) {
        final double pi = 3.14;
//        pi = 3.15;//error...

        //상수 변수를 선언한다면 코드 가독성을 위해서 all 대문자..
        final double PI = 3.14;

    }
}

//직원을 코드로 표현하기 위해서 .. 클래스를 하나 만든다고 가정해보자..
class Employee {

    final int DEPARTMENT_SALE = 0;
    final int DEPARTMENT_DEV = 1;
    final int DEPARTMENT_MARKETING = 2;

    //직원의 부서를 저장하기 위한 변수..
    int department;

    void calcBonus() {
        // 0 - sale, 1 - dev
        //코드의 명확성을 위해서.. 상수 변수를 선언해서... 지정하는것이 일반적..
        if(department == 0){
            System.out.println("연봉에 10을 곱합니다.");
        }else if(department == 1){
            System.out.println("연봉에 20을 곱합니다.");
        }

        if(department == DEPARTMENT_SALE){
            System.out.println("연봉에 10을 곱합니다.");
        }else if(department == DEPARTMENT_DEV){
            System.out.println("연봉에 20을 곱합니다.");
        }
    }
}
