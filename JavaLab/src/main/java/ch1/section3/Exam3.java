package ch1.section3;

//main 함수 테스트.....
public class Exam3 {
    //JVM 에 의해 실행될 클래스는 main 함수를 가지고 있어야..
    public static void main(String[] args) {
        //jvm 에 의해 전달되는 데이터 확인..
        for(String arg: args){
            System.out.println(arg);//출력..
        }
    }
}
//class 파일이 위치하는 java/main 디렉토리에서 명령어 입력..
//java ch1.section3.Exam3

//java ch1.section3.Exam3 hello 10
//hello
//10












