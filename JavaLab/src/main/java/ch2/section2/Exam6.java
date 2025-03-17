package ch2.section2;

//while 테스트.......................

public class Exam6 {
    public static void main(String[] args) {
        //1~10까지 숫자 더하기...
        int sum = 0;
        int i = 1;
        while(i <= 10){
            sum += i;
            i++;
        }
        System.out.println("1~10 : " + sum);//1~10 : 55

        //1~10 더하기, 짝수만...
        sum = 0;
        i = 1;
        while (i <= 10){
            if(i % 2 == 0){
                sum += i;
            }
            i++;
        }
        System.out.println("1~10 짝수 : " + sum);//1~10 짝수 : 30

        //구구단 찍기.. 2단부터 9단까지..
        int dan = 2;
        while (dan <= 9){
            System.out.println("======== "+dan+" 단");
            int no = 1;
            while (no <= 9){
                System.out.println(dan + " * " + no + " = "+ (dan*no));
               no++;
            }
            dan++;
        }
    }
}
