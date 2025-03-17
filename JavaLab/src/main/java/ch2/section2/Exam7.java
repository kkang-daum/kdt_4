package ch2.section2;

import java.util.Scanner;

public class Exam7 {
    public static void main(String[] args) {
        //JAM 으로 실행시키면서 준 데이터는 main 함수의 args 에...
        //>java Exam7 hello world

        //jvm 에서 실행 도중에.. 콘솔 창에서 유저가 입력한 데이터를 획득..
        Scanner input = new Scanner(System.in);

        //사용자가 입력한 데이터로 업무를 진행.. 무한 반복...
        while (true){
            System.out.println("메시지를 입력한 후 Enter를 치세요. (종료하시려면 quit)");
            //유저 입력 문자열 획득..
            //nextLine() 함수를 만나면 대기상태.. 입력할 때까지..
            //입력하고 enter 치면.. 유저 입력데이터를 message 에 대입..
            String message = input.nextLine();
            //equals() - 문자열이 같은지 비교...
            if(message.equals("quit")){
                //종료되게 만들면 된다..
                //while 을 빠져나가게..
                break;//while 이 끝난다..
            }
            System.out.println("입력한 메시지 : " + message);
        }
        input.close();
    }
}
