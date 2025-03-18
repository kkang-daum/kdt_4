package ch2.section2.sub10;

public class Quiz5 {

    public static void main(String[] args) {
        for (int a = 1; a <= 9; a++){
            for (int i = 2; i <= 9; i++){
                System.out.print(i + "x" + a + "=" + (i*a) + "\t");
            }
            System.out.println();
        }
    }
}

