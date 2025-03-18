package ch2.section2.sub6;

import java.util.Scanner;

public class Quiz3 {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        System.out.print("enter prime num : ");

        int num = stdin.nextInt();
        Quiz3 quiz3 = new Quiz3();  // 객체 생성

        // 2부터 시작해 자신보다 작은 숫자까지 나누어 0이 나오지 않을 때 소수
        if (quiz3.JudgePrime(num)) {
            System.out.print("prime");
        } else {
            System.out.print("not prime");
        }
    }

    boolean JudgePrime(int num) {
        int cnt = 2;
        while (num % cnt != 0) {
            cnt++;

            if (cnt <= num) {
                return true;
            }
        }

        return false;
    }
}
