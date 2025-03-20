package ch3.section4_static;

//static test...................

class Count {
    int count;
    static int totalCount;

    void method1(){}
    static void method2(){}
}

public class Exam1 {
    public static void main(String[] args) {
//        method1();//error..
//        Count.method1();//error

        //static 멤버는 객체 생성 전에 이용 가능..
        //클래스명.멤버명..
        Count.method2();//ok...

        Count c1 = new Count();
        Count c2 = new Count();
        Count c3 = new Count();

//        Count.count++;//error.. object member 는 객체명으로
        c1.count++;
        Count.totalCount++;
        c2.count++;
        Count.totalCount++;
        c3.count++;
        Count.totalCount++;

        System.out.println(Count.totalCount+" : "+c1.count);
        System.out.println(Count.totalCount+" : "+c2.count);
        System.out.println(Count.totalCount+" : "+c3.count);
        //3 : 1
        //3 : 1
        //3 : 1

        //static 멤버.. 정칙은 클래스명.멤버명..
        //객체로 접근도 허용하기는 한다.. 권장하지는 않는다.
        c1.totalCount++;
        c2.totalCount++;
        c3.totalCount++;

        //6,6,6,6
        System.out.println(Count.totalCount+","+c1.totalCount+","+c2.totalCount+","+c3.totalCount);
    }
}
