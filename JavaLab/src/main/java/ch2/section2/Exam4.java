package ch2.section2;

//if 조건문 테스트......

public class Exam4 {
    public static void main(String[] args) {
        //if
        int javaScore = 90;
        if (javaScore >= 60) {
            if(javaScore >= 80){
                System.out.println("고득접입니다.");
            }
            System.out.println("합격입니다.");
        }

        //if - else : false 일때 실행시켜야 하는 로직이 있을 때...
        javaScore = 20;
        if (javaScore >= 60){
            System.out.println(javaScore + "점은 합격입니다.");
        }else {
            if(javaScore <= 30){
                System.out.println(javaScore + "점은 패널티 대상입니다.");
            }else {
                System.out.println(javaScore + "점은 불합격입니다.");
            }
        }

        //if - else if : 하나의 상황에 조건을 여러개 걸어서..
        //else if 를 몇개를 걸든.. 위에서부터 판단... 처음 만족하는 부분만 실행
        javaScore = 89;
        if(javaScore >= 90){
            System.out.println(javaScore + "점은 A 등급입니다.");
        }else if(javaScore >= 80){
            System.out.println(javaScore + "점은 B 등급입니다.");
        }else if(javaScore >= 70){
            System.out.println(javaScore + "점은 C 등급입니다.");
        }else if(javaScore >= 60){
            System.out.println(javaScore + "점은 D 등급입니다.");
        }else {
            System.out.println(javaScore + "점은 F 등급입니다.");
        }

        //89점은 B 등급입니다.
        //89점은 D 등급입니다.

        if(javaScore >= 60){
            System.out.println(javaScore + "점은 D 등급입니다.");
        }else if(javaScore >= 70){
            System.out.println(javaScore + "점은 C 등급입니다.");
        }else if(javaScore >= 80){
            System.out.println(javaScore + "점은 B 등급입니다.");
        }else if(javaScore >= 90){
            System.out.println(javaScore + "점은 A 등급입니다.");
        }else {
            System.out.println(javaScore + "점은 F 등급입니다.");
        }
    }
}
