package ch2.section2;

import java.util.Scanner;

public class Quiz3 {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        System.out.print("소수인지 판단할 숫자 :");
        int num = stdin.nextInt();

        //나누는 숫자..
        int divisor = 2;
        boolean isPrime = true;

        while (divisor < num){
            if(num % divisor == 0){
                isPrime = false;
                break;
            }
            divisor++;
        }
        if(num ==2 || isPrime){
            System.out.println(num + " 은 소수입니다.");
        }else {
            System.out.println(num + " 은 소수가 아닙니다.");
        }
    }
}
