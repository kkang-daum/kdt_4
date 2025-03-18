package ch2.section2;

import java.util.Scanner;

public class Quiz4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("양수를 입력하세요 : ");

        int inputNum = scanner.nextInt();

        //2부터 유저가 입력한 숫자까지.. 1 증가시키면서 소수인지를 판단..
        for(int num = 2; num <= inputNum; num++){
            if(num == 2){
                System.out.print(num + " ");
            }else {
                boolean isPrime = true;
                for(int divisor = 2; divisor <= (num / 2); divisor++){
                    if(num % divisor == 0){
                        isPrime = false;
                        break;
                    }
                }
                if(isPrime){
                    System.out.print(num + " ");
                }
            }
        }
    }
}
