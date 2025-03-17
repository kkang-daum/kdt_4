package ch2.section2;

//증감 연산자, 비교 연산자 테스트......

public class Exam2 {
    public static void main(String[] args) {
        //증감 연산자, 연산자 우선순위....
        int a = 10;
        a++;
        System.out.println(a);//11
        ++a;
        System.out.println(a);//12

        int b = 10;
        b--;
        System.out.println(b);//9
        --b;
        System.out.println(b);//8

        //증감 연산자의 위치를 신경써야 하는 경우는 같은 라인에서 다른 연산자와
        //같이 쓰이는 경우...
        int c = 10;
        int d = 10;
        System.out.println(++c);//11
        System.out.println(d++);//10

        //논리 연산자, 하나 두개 차이....
        int e1 = 10;
        int f1 = 10;
        //조건을 판단하는 구문...
        if(--e1 > 10 & --f1 > 10){
            //조건이 true 인 경우 실행되는 영역..
            System.out.println("if 1 - true");
        }

        int e2 = 10;
        int f2 = 10;
        if(--e2 > 10 && --f2 > 10){
            System.out.println("if 2 - true");
        }

        //&, && 에 따라 왼쪽이 false 일때, 오른쪽이 실행되는지에 대한 차이....
        //9,9,9,10
        System.out.println(e1 + "," + f1 + "," + e2 + "," + f2);
    }
}
