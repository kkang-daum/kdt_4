package ch2.section2;

//break, continue test....................

public class Exam9 {
    public static void main(String[] args) {
        //continue....
        for(int i = 1; i<=10; i++){
            if(i == 5){
                continue;
            }
            System.out.println("i : " + i);
        }
        //i : 1
        //i : 2
        //i : 3
        //i : 4
        //i : 6
        //i : 7
        //i : 8
        //i : 9
        //i : 10

        System.out.println("---------------------------------");
        //break....
        for(int i = 1; i<=10; i++){
            if(i == 5){
                break;
            }
            System.out.println("i : " + i);
        }
        //i : 1
        //i : 2
        //i : 3
        //i : 4
        System.out.println("---------------------------------");
        //중첩 루프... no label...
        for(int i=1; i <= 5; i++){
            for(int j = 1; j <= 5; j++){
                if(j > i)
                    break;
                System.out.println("i, j : "+ i+ ", "+ j);
            }
        }
        System.out.println("---------------------------------");
        //중첩 루프... label....
        outer: for(int i=1; i <= 5; i++){
            for(int j = 1; j <= 5; j++){
                if(j > i)
                    break outer;
                System.out.println("i, j : "+ i+ ", "+ j);
            }
        }
        //i, j : 1, 1
    }
}
