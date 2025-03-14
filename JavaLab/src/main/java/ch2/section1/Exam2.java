package ch2.section1;

//변수 선언, 초기화..

public class Exam2 {
    //변수 선언과 동시에 초기화..
    int data1 = 0;

    //동일 타입의 변수를 여러개 선언한다면.. 한줄에 선언도 가능하다..
    int data2, data3, data4 = 10;

    //클래스 멤버 변수.. 클래스 내에 선언되는 변수..
    //클래스 멤버 변수는 선언과 동시에 초기값을 주지 않으면..
    //default 초기화 된다.. 자동 초기화 된다..
    //숫자 타입은 0, boolean 타입은 false, 객체타입은 null
    boolean data5;
    String data6;//String 이라는 클래스로 선언한 객체..


    void sayHello(){
        //data2 : 0, data3 : 0, data4 :10
        System.out.println("data2 : "+ data2 +", data3 : "+data3+", data4 :"+data4);
        //data5 : false, data6 : null
        System.out.println("data5 : "+ data5 +", data6 : "+data6);

        //어떤 함수내에 선언된 변수..
        //함수가 실행되어야 메모리가 확보되는 변수..
        //local variable (함수의 지역변수)
        int data7;
        //local variable default 초기화 시키지 않는다..
        //선언과 동시에 초기화 하지 않아도 되지만.. 이용하기 전에 초기화는 꼭 명시적으로 해야 한다.
//        System.out.println(data7);//error
//        data7++;//error
        data7 = 20;
        System.out.println(data7);
        data7++;

        //변수가 이용되는 영역 (scope)
        //클래스 { } - 클래스 전체에서
        //함수 { } = 함수 내에서..
        //for( ) { } - for 안에서만..
        for(int i = 0; i < 1; i++){
            int data8 = 0;
            data8 ++;
        }
//        data8 ++;//error

        if(true){
            int data9 = 10;
            data9 ++;
        }
//        data9 ++;//error...
    }

    public static void main(String[] args) {
        new Exam2().sayHello();

        //클래스 멤버 변수를 이용...
        //클래스 전역에서 사용 가능..
        new Exam2().data2 = 40;
        //특정 함수내에 선언된 변수는 그 함수내에서만 사용이 가능하다.
//        new Exam2().data7 = 40;//error...
    }
}
//변수 선언 위치...  객체 멤버 변수, local variable
//default 초기화?
//이용 범위..