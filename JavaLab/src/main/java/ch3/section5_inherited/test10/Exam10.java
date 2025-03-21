package ch3.section5_inherited.test10;

//공통 코드 개발자가 작성했다고 가정..
class Employee {
    String name;
    int salary = 100;

    int calcBonus(){
        System.out.println("Employee 보너스 계산");
        return salary*12;
    }
}

class Calc {
    public static void calcBonus(Employee e){
        System.out.println(e.calcBonus());
    }
}

//다른 개발자의 코드..
class Salesman extends Employee {
    @Override
    int calcBonus() {
        System.out.println("Salesman 보너스 계산");
        return salary*12*4;
    }
}
//또 다른 개발자가..
class Consultant extends Employee {
    @Override
    int calcBonus() {
        System.out.println("Consultant 보너스 계산");
        return salary*12*2;
    }
}

public class Exam10 {
    public static void main(String[] args) {
        Calc.calcBonus(new Salesman());
        Calc.calcBonus(new Consultant());
    }
}
