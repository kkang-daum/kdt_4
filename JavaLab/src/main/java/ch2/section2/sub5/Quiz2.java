package ch2.section2.sub5;

public class Quiz2 {
    public static void main(String[] args) {
        int month = 8;

        switch (month) {
            case 12:
            case 1:
            case 2:
                System.out.println("겨울입니다.");
                break;
            case 3:
            case 4:
            case 5:
                System.out.println("봄입니다.");
                break;
            case 6:
            case 7:
            case 8:
                System.out.println("여름입니다.");
                break;
            case 9:
            case 10:
            case 11:
                System.out.println("가을입니다.");
                break;
        }
    }
}
