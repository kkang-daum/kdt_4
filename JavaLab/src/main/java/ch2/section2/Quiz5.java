package ch2.section2;

public class Quiz5 {
    public static void main(String[] args) {
        //곱해지는 숫자 증가..
        for(int num = 1; num <= 9; num++){
            //단수 증가..
            for(int dan = 2; dan <= 9; dan++){
                System.out.print(dan + " * " + num + " = " + (dan*num < 10 ? (" "+dan*num) : (dan * num) )+ "    ");
            }
            //개행을 위해서..
            System.out.println();
        }
    }
}
