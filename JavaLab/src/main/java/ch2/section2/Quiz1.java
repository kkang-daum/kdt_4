package ch2.section2;

public class Quiz1 {
    public static void main(String[] args) {
        int month = 11;

        if(month == 3){
            System.out.println("봄입니다.");
        }else if(month == 4){
            System.out.println("봄입니다.");
        }else if(month == 5){
            System.out.println("봄입니다.");
        }else if(month == 6){
            System.out.println("여름입니다.");
        }else if(month == 7){
            System.out.println("여름입니다.");
        }else if(month == 8){
            System.out.println("여름입니다.");
        }else if(month == 9){
            System.out.println("가을입니다.");
        }else if(month == 10){
            System.out.println("가을입니다.");
        }else if(month == 11){
            System.out.println("가을입니다.");
        }else if(month == 12){
            System.out.println("겨울입니다.");
        }else if(month == 1){
            System.out.println("겨울입니다.");
        }else if(month == 2){
            System.out.println("겨울입니다.");
        }else {
            System.out.println("1~12의 숫자가 아닙니다.");
        }

        if(month == 3 || month == 4 || month == 5){
            System.out.println("봄입니다.");
        }else if(month == 6 || month == 7 || month == 8){
            System.out.println("여름입니다.");
        }else if(month == 9 || month == 10 || month == 11){
            System.out.println("가을입니다.");
        }else if(month == 12 || month == 1 || month == 2){
            System.out.println("겨울입니다.");
        }else {
            System.out.println("1~12의 숫자가 아닙니다.");
        }
    }
}
