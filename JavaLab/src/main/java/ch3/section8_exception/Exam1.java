package ch3.section8_exception;

public class Exam1 {
    void method1(int num1, int num2){
        int result = num1 / num2;
        System.out.println("end... "+result);
    }

    void method2(int num1, int num2){
        try {
            //정상 실행되어야 하는 부분..
            System.out.println("start....");
            int result = num1 / num2;
            System.out.println("end... " + result);
        }catch (Exception e){
            //try 부분에 exception 발생 시점에 실행되어야 하는 부분..
            //catch 매개변수로 발생한 exception 정보가 넘어온다..
            System.out.println("catch......");
            //현재 발생한 에러를 로그 출력해줘..
            e.printStackTrace();
        }finally {
            System.out.println("finally.....");
        }
        System.out.println("end.....");
    }

    void method3(int num, String name){
        try {
            int result = 10 / num;
            name.substring(1);
        }catch (ArithmeticException e){
            System.out.println("ArithmeticException...");
        }catch (NullPointerException e){
            System.out.println("NullPointerException...");
            e.printStackTrace();
        } catch (Exception e){
            //예외 정보를 표현하는 클래스... 타입..
            //모든 exception 의 최상위 타입이 Exception
            //모든 예외 여기서 처리... 위에 걸리지 않는..
            System.out.println("Exception");
        }
//        catch (NullPointerException e){
//            //이렇게는 못쓴다..
//            //catch 가 여러개 들어갈 수 있지만. 위에서부터 순차적인 판단이며
//            //한순간에 하나의 catch 만 실행된다..
//            //위에 상위 타입의 exception 이 선언되어 있고.. 그 아래에
//            //하위 타입의 exception 이 선언되있으면.. 어자피.. 이 부분은
//            //실행될 수가 없다..
//        }
    }

    public static void main(String[] args) {
        Exam1 obj = new Exam1();
        obj.method1(10, 5);
        //Exception in thread "main" java.lang.ArithmeticException
        //에러 발생, 대체 준비하지 않았다.. 프로그램 종료 상황..
//        obj.method1(10, 0);

        obj.method2(10, 5);
        //정상상황===============
        //start....
        //end... 2
        //finally.....
        //end.....

        obj.method2(10, 0);
        //에러가 발생했지만.. 프로그램은 정상적으로 실행되었다..
        //에러상황===================
        //start....
        //catch......
        //finally.....
        //end.....

        obj.method3(0, "kim");//ArithmeticException...
        obj.method3(10, null);//NullPointerException...
    }
}
