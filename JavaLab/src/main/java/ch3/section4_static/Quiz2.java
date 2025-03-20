package ch3.section4_static;

//test1.........................................
//class Employee {
//    String name;
//    int department;
//
//    Employee(String name, int department){
//        this.name = name;
//        this.department = department;
//    }
//}
//public class Quiz2 {
//    public static void main(String[] args) {
//        //직원의 부서를 숫자값으로 이용하는 것은 적절해 보인다..
//        //코드내에.. 부서가 숫자로 명시가 되다 보니..
//        //윗 코드를 분석하거나 메뉴얼을 읽지 않는한.. 0이 어느 부서인지..
//        //판단하기 힘들다..
//        //코드 가독성에 문제가 있다... ==>유지보수성 저하..
//        //==>동일한 상수 값으로 부서를 표현한다고 하더라도 상수 변수를 만들어서
//        //변수명으로..
//        Employee e1 = new Employee("kim", 0);
//        Employee e2 = new Employee("lee", 1);
//    }
//}


//test2......................
//부서를 지칭하는 상수 변수를 꼭 별도의 클래스로 묶어야 하는 것은 아니다..
//그런데 같은 의미를 지칭하는 상수 변수가 좀 많다면 아래처럼 별도의 클래스로 묶기도 한다
//class DepartmentCode {
//    //상수 변수를 만들기는 했지만... 이 변수를 이용하려면.. 객체를 생성하고 이용
//    //이 상수 변수 값이 객체 여러개 생성시에.. 각각 메모리에 다른 데이터를
//    //유지하기 위한 변수인가?
//    //그런 변수들은 static 으로 선언해서.. 한 곳에서만 유지되게...
//    final int SALES = 0;
//    final int DEVELOPER = 1;
//    final int MARKETING = 2;
//}
//class Employee {
//    String name;
//    int department;
//
//    Employee(String name, int department){
//        this.name = name;
//        this.department = department;
//    }
//}
//public class Quiz2 {
//    public static void main(String[] args) {
//        DepartmentCode code = new DepartmentCode();
//        Employee e1 = new Employee("kim", code.SALES);
//        Employee e2 = new Employee("lee", code.DEVELOPER);
//
////        if(e1.department == code.DEVELOPER){}
//    }
//}

//test3.........................
class DepartmentCode {
    final static int SALES = 0;
    final static int DEVELOPER = 1;
    final static int MARKETING = 2;
}
class Employee {
    String name;
    int department;

    Employee(String name, int department){
        this.name = name;
        this.department = department;
    }
}
public class Quiz2 {
    public static void main(String[] args) {
//        DepartmentCode code = new DepartmentCode();
        Employee e1 = new Employee("kim", DepartmentCode.SALES);
        Employee e2 = new Employee("lee", DepartmentCode.DEVELOPER);

//        if(e1.department == code.DEVELOPER){}
    }
}
