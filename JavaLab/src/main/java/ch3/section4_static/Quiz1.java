package ch3.section4_static;

public class Quiz1 {



    int a = 0;

    void a(){}

    //아래는 실행구문이다..
    //실행 구문은 { } 내에서만, 클래스 {} 가 아닌..
//    a++;
//    a();
    void b(){
        //함수 호출시점의 실행 영역..
        a++;
        a();

        if(true){
            //조건이 만족했을때의 실행 영역..
            a++;
            a();
        }
        for(int i=0; i<2;i++){
            //실행영역..
            a++;
            a();
        }
    }

    //객체의 메모리 할당 시점에.. 즉 클래스 영역에서.. 실행구문을 담아서..
    //객체 생성과 동시에 실행시킬 코드가 있다면..
    //어떤 조건도 없기때문에.. 인지만 되면 무조건 실행..
    //이 역할자가 생성자의 {} 가 있다보니.. 에러가 발생하지 않지만..
    //아래처럼 작성을 잘 안한다.
    {
        System.out.println("1");
    }
    //static 멤버 메모리 할당 시점의 실행 구문..
    static {
        System.out.println("2");
    }
    Quiz1(){
        System.out.println("3");
    }
    Quiz1(int no){
        this();
        System.out.println("4");
    }

    public static void main(String[] args) {
        new Quiz1();
        new Quiz1(10);
    }
}

//2 -> 1 -> 3 -> 1 -> 3 -> 4
