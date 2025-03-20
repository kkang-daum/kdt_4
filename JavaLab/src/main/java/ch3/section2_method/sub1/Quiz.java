package ch3.section2_method.sub1;

class Math{
    //매개변수가 정수 2개
    int sum(int a, int b){
        return a+b;
    }
    //매개변수가 정수 3개
    int sum(int a, int b, int c){
        return a+b+c;
    }
    //매개변수가 배열 하나
    int sum(int[] a){
        int sum = 0;
        for(int i = 0; i < a.length ; i++){
            sum += a[i];
        }
        return sum;
    }

}

public class Quiz {
    public static void main(String[] args) {
        Math obj = new Math();
        int[] a = {1,2,3,4,5,6,7,8,9,10};

        System.out.println(obj.sum(2, 6));
        System.out.println(obj.sum(2, 6, 10));
        System.out.println(obj.sum(a));

    }
}
