package ch3.section8_exception;

public class Exam2 {
    void method1(){
        try {
            int result = 10 / 0;
        }catch (Exception e){

        }
    }

    void method2() throws ArithmeticException {
        try{
            int result = 10/0;
        }catch (Exception e){
            //실제 exception 이 전파되는 순간..
            //return 과 동일한 효과.. 이 라인 아랫줄 실행 안된다..
            throw new ArithmeticException();
        }
    }

    void method3() throws ArithmeticException {
        try{
            throw new ArithmeticException();//가능.. throw 를 catch 에서만
            //사용하는 것은 아니다.. 함수 전역에서
        }catch (ArithmeticException e){
//            throw e;//ok....catch 의 매개변수가 자신이 발생한 예외상황..
            //그걸 그대로 발생..

            //throws 에 선언된 exception 보다 상위 타입의 exception 을 던질
            //수 없다..
//            throw new Exception();//error..
        }
    }

    public static void main(String[] args) {
        Exam2 obj = new Exam2();
        System.out.println("step1");
        obj.method1();
        System.out.println("step2");
        //step1
        //step2
        //==>method1 에서 exception 이 발생했고.. catch 에서 대응했다..
        //이 함수를 호출한 쪽에서는 exception 발생상황을 모른다..

        //throw 에 의해.. 이 라인에서 에러가 발생한 효과..
        try {
            obj.method2();
        }catch (Exception e){

        }
    }
}
