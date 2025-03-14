package ch2.section1;

//식별자, 예약어 테스트.....

public class Exam1 {
    //아래는 모두 가능...
    int count = 0;
    int count1 = 10;//숫자가 식별자에 사용은 가능하다. 단 첫글자로 못쓴다.
    int count1data = 10;
    //식별자에 사용할수 있는 특수 키는 _, $
    int count_data = 10;
    int count$data = 10;
    //원한다면.. 각국 언어도 가능하다..
    int 카운트 = 10;

    //아래는 모두 에러...
//    int 1count = 10;
//    int count-1 = 10;
//    int count*data = 10;
//    int count data = 10;
//    int final = 10;//예약어..

    public static void main(String[] args) {
        Exam1 객체 = new Exam1();
        객체.카운트 = 20;
        System.out.println(객체.카운트);
    }
}
