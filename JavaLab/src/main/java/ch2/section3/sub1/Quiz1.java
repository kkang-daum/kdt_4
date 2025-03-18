package ch2.section3.sub1;

public class Quiz1 {
    public static void main(String[] args) {
        int[] arr = {76,92,49,78,83};

        int max = arr[0], min = arr[0], sum = arr[0];
        for(int i = 1; i< arr.length; i++){
            if(min > arr[i]) min = arr[i];
            if(max < arr[i]) max = arr[i];
            sum += arr[i];
        }
        System.out.println("최고 점수 : "+max);
        System.out.println("최저 점수 : "+min);
        System.out.println("점수 총합 : "+sum);
        System.out.println("점수 평균 : "+(sum/arr.length));

    }
}
