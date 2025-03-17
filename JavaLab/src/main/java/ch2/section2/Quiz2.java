package ch2.section2;

public class Quiz2 {
    public static void main(String[] args) {
        int month = 8;

        switch (month){
            case 3:
            case 4:
            case 5:
                System.out.println("봄.....");
                break;
            case 6:
            case 7:
            case 8:
                System.out.println("여름....");
                break;
            case 9:
            case 10:
            case 11:
                System.out.println("가을...");
            case 12:
            case 1:
            case 2:
                System.out.println("겨울....");
            default:
                System.out.println("1~12 사이의 정수값이어야 합니다.");
        }
    }
}
