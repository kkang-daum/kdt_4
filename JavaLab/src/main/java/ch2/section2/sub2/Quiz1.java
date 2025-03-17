package ch2.section2.sub2;

public class Quiz1 {
    public static void main(String[] args) {
        int i = 4;
        if (i>=3 && i<=5) {
            System.out.println("봄입니다.");
        } else if (i>=6 &&i<=8) {
            System.out.println("여름입니다.");
        } else if (i>=9 &&i<=11) {
            System.out.println("가을입니다.");
        } else if (i==12 || i==1 || i==2){
            System.out.println("겨울입니다.");
        } else {
            System.out.println("알 수 없는 계절입니다.");
        }

    }
}
