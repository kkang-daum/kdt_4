package ch2.section2.sub11;

public class Quiz5 {
    public static void main(String[] args) {
        for (int i = 1; i <= 9; i++) {
            for (int j = 2; j <= 9; j++) {
                System.out.print(j + " * " + i + " = " + i * j + "\t");
            }
            System.out.print("\n");
        }
    }
}
