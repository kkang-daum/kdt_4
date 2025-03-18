package ch2.section2;

//for loop test........................

public class Exam8 {
    public static void main(String[] args) {
        //1~10 더하기..
        int sum = 0;
        for(int i = 1; i <= 10; i++){
            sum += i;
        }
        System.out.println("sum : "+ sum);//sum : 55

        //1~10, 홀수만.. 더하기..
        sum = 0;
        for(int i = 1; i <= 10; i++){
            if(i % 2 != 0)
                sum += i;
        }
        System.out.println("sum : "+ sum);//sum : 25

        //구구단....
        for(int dan = 2; dan <= 9; dan++){
            System.out.println("==== " + dan + "단 =======");
            for(int i = 1; i <= 9; i++){
                System.out.println(dan + "*" + i + " = " + (dan*i));
            }
        }
    }
}
