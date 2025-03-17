package ch2.section2.sub1;

public class Quiz1 {
    public static void main(String[] args) {
        int season = 11;

        if(2<season && season<6){
            System.out.println("봄입니다.");
        }else if(5<season && season<9){
            System.out.println("여름입니다.");
        }else if(8<season && season<12){
            System.out.println("가을입니다.");
        } else if (season==1 | season == 12 | season ==2) {
            System.out.println("겨울입니다");
        }
    }
}
