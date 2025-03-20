package ch3.section2_method;

class Math {
    int plus(int no1, int no2){
        return no1 + no2;
    }
    int plus(int no1, int no2, int no3){
        return no1 + no2 + no3;
    }
    int plus(int[] array){
        int sum = 0;
        for(int no : array){
            sum += no;
        }
        return sum;
    }
}

public class Quiz1 {
    public static void main(String[] args) {
        Math m = new Math();
        System.out.println(m.plus(10, 20));
        System.out.println(m.plus(10, 20, 30));
        System.out.println(m.plus(new int[]{10, 20, 30, 40, 50}));
    }
}
