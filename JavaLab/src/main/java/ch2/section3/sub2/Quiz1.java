package ch2.section3.sub2;

import java.util.Arrays;

public class Quiz1 {
    public static void main(String[] args) {
        int[] arr = {76, 92, 49, 78, 83};
        int max = 0;
        int min = Integer.MAX_VALUE;
        int sum = 0;

        for (int num : arr) {
            if (num > max) {
                max = num;
            }
            if (num < min) {
                min = num;
            }
            sum += num;
        }
        System.out.println("최고 점수 :" + max);
        System.out.println("최저 점수 :" + min);
        System.out.println("점수 총합 :" + sum);
        System.out.println("점수 평균 :" + sum / arr.length);

    }
}
