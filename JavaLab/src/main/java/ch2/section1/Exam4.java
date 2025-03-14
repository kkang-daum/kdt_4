package ch2.section1;

public class Exam4 {
    public static void main(String[] args) {
        int a = 10;
        double d = a;//int -> double... 암시적 캐스팅.. 자동으로
//        int a2 = d;//error... double -> int
        int a2 = (int)d;//명시적 캐스팅..

        double d3 = 20.34;
        int a3 = (int)d3;
        System.out.println(a3);//20 - 캐스팅 하면서 소수점 버림..

        //char 도 정수로 표현이 될 수 있다...
        char c = 'A';
        int a4 = c;
        System.out.println("A : "+a4);//A : 65
        //ascii 코드표 - 각 문자에 해당되는 정수값이 있다..

        //영문자가 아닌 각국 언어의 문자도 정수화 될수는 있지만..
        //큰 수로 나와서 큰 의미는 없다..
        //char 로 정수화 시켜서 로직을 잡는 경우 일반적으로 영문자 알파벳..
        char c2 = '가';
        int a5 = c2;
        System.out.println("A : "+a5);//A : 44032

        //가장 많이 발생하는 캐스팅은...
        //"10" <-> 10
        //String <-> int
        //캐스팅은.. 기초타입의 데이터들만...
        //String 은 문자열을 쉽게 사용하기 위한 클래스이다.. 캐스팅 안된다.
        //별도의 함수를 제공하고 그 함수를 이용해서 변형시킨다..
        String str = "10";
        int intData = Integer.parseInt(str);

        String str2 = String.valueOf(20);
        System.out.println((str + 10)+ "," + (intData + 10));//1010,20

        String str3 = "hello";
        int intStr3 = Integer.parseInt(str3);//runtime error.....
        //Exception in thread "main" java.lang.NumberFormatException: For input string: "hello"
    }
}
