package ch2.section2;

//비트 연삱자, 3항연산자...

public class Exam3 {
    public static void main(String[] args) {
        int a = 3; //0000 0011
        int b = 5; //0000 0101

        System.out.println("(a & b) : " + (a & b));//1 - 0000 0001
        System.out.println("(a | b) : " + (a | b));//7 - 0000 0111
        System.out.println("(a ^ b) : " + (a ^ b));//6 - 0000 0110
        System.out.println("(a >> 1) : " + (a >> 1));//0000 0001 - 1
        System.out.println("(a << 1) : " + (a << 1));//0000 0110 - 6

        //3항 연산자.....
        //조건에 따라 값이 다르게 나올때 사용...
        int javaScore =  83;
        //60점 이상이면 합격, 미만이면 불합격이라고 출력하고 싶다..
        System.out.println(javaScore >= 60 ? "합격" : "불합격");//합격

        javaScore = 40;
        System.out.println(javaScore >= 60 ? "합격" : "불합격");//불합격

        String result = javaScore >= 60 ? "합격" : "불합격";

        int result2 = javaScore >= 60 ? 100 : 0;
    }
}
