package ch2.section3;

public class Quiz1 {
    public static void main(String[] args) {
        int[] array = {76, 92, 49, 78, 83};

        int max = array[0];//76
        int min = array[0];//76
        int sum = array[0];//76
        int avg = 0;

        for(int i = 1; i < array.length; i++){
            if(min > array[i]){
                min = array[i];
            }
            if(max < array[i]){
                max = array[i];
            }
            sum += array[i];
        }
        avg = sum / array.length;

        System.out.println("min : "+min);
        System.out.println("max : "+max);
        System.out.println("sum : "+sum);
        System.out.println("avg : "+avg);
    }
}
