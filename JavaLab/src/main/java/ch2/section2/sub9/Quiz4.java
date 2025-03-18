package ch2.section2.sub9;

import java.util.Scanner;

public class Quiz4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("양수를 입력하시오.");
        int num = input.nextInt();

        for(int i = 2; i <= num; i++) {
            for(int j = 2; j <= i; j++) {
                if (j == i) System.out.print(j + " ");
                if (i % j == 0) break;
            }

        }
    }
}
