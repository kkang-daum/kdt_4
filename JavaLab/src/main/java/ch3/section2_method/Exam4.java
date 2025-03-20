package ch3.section2_method;

//함수 오버로딩 테스트....

//이 클래스의 함수를 이용하면 애플리케이션의 로그를 출력시켜 준다는 가정..
//개발시에 디버깅 목적으로 System.out.println() 해서 콘솔창에 로그를 남기는 것도
//중요하지만..
//운용 환경에서도 다양한 로그를 남긴다.. 영속 저장..file, db...network
class Log {
    void log(String arg) { System.out.println(arg);}
    void logInt(int arg) { System.out.println(arg); }
    void logBoolean(boolean arg) { System.out.println(arg);}

    //동일 행동이라면 동일 이름으로..
    void log(int arg){ }
    void log(boolean arg) {}
}

public class Exam4 {
    public static void main(String[] args) {
        Log obj = new Log();
        obj.log("hello");
        obj.logInt(10);
        obj.logBoolean(true);

        obj.log(10);
        obj.log(true);
    }
}
