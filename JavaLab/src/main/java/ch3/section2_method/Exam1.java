package ch3.section2_method;

public class Exam1 {

    void method1() {
        System.out.println("method1 call...");
    }
    void method2(int arg1, String arg2, boolean arg3, int arg4){
        System.out.println(arg1+","+arg2+","+arg3+","+arg4);
    }

    String method3(int num){
        int sum = 0;
        for(int i = 1; i <= num; i++){
            sum += i;
        }
        return "1부터 "+num+"까지 더한 결과는 "+sum+" 입니다.";
    }

    void method4(){
        System.out.println("method4 start...");
        System.out.println("method4 end...");
    }
    void method5(){
        System.out.println("method5 start...");
        //다른 함수 호출..
        method4();
        System.out.println("method5 end...");
    }
    void method6(){
        System.out.println("method6 start...");
        //다른 함수 호출..
        method5();
        System.out.println("method6 end...");
    }


    public static void main(String[] args) {
        //method1 호출..
        //객체지향이다.. 함수, 변수가 클래스 멤버이다..
        //클래스의 객체를 먼저 생성하고.. 객체.함수명()
        Exam1 obj = new Exam1();
        obj.method1();
        obj.method1();

        //매개변수, 순서, 갯수, 타입을 맞추어서 호출해야..
        obj.method2(10, "hello", true, 20);

        String result = obj.method3(10);
        System.out.println(result);

        System.out.println("main start...");
        obj.method6();
        System.out.println("main end....");
    }
}

//main start...
//method6 start...
//method5 start...
//method4 start...
//method4 end...
//method5 end...
//method6 end...
//main end....
