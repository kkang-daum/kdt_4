package ch2.section2.sub7;

import java.util.Scanner;
import java.io.*;
import java.lang.*;

public class Quiz3 {

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        System.out.print("소수인지 판단할 숫자:");
        int num = stdin.nextInt();
        if (num == 2 || num == 3) {
            System.out.println("소수입니다.");
            return;
        }
        boolean check = true;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                check = false;
                break;
            }
        }
        if (check) {
            System.out.println("소수입니다.");
        } else
            System.out.println("소수가 아닙니다.");
    }
}