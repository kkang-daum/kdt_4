package ch3.section2_method.sub2;

class Math {
    int sum(int a, int b) {
        return a + b;
    }

    int sum(int a, int b, int c) {
        return a + b + c;
    }

    int sum(int[] intArray) {
        var result = 0;
        for (int i = 0; i < intArray.length; i++) {
            result += intArray[i];
        }
        return result;
    }
}

public class Quiz1 {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Math math = new Math();

        System.out.println(math.sum(1, 2));
        System.out.println(math.sum(1, 2, 3));
        System.out.println(math.sum(array));
    }
}
