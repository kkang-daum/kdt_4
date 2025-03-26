package ch4.section2.util;

import java.util.regex.Pattern;

public class Exam2 {
    public static void main(String[] args) {
        //유저에게 받은 데이터가 있다고 가정..
        String tel = "010-1234";
        String email = "aa.com";

        //유저에게 받은 데이터는 비 신뢰적이라고 판단.. 유효성 검증하고자 한다.
        //우리가 원하는 패턴의 문자열인지를 검사하고자 한다..
        //원하는 패턴을 명시한 정규표현식 문자열을 준비..
        String telPattern = "01[0-9]-\\d{3,4}-\\d{4}";
        String emailPattern = "\\w+@\\w+\\.\\w+(\\.\\w+)?";

        if(Pattern.matches(telPattern, tel)){
            System.out.println("정상적인 전화번호");
        }else {
            System.out.println("잘못된 전화번호를 입력하셨습니다.");
        }
        if(Pattern.matches(emailPattern, email)){
            System.out.println("정상적인 이메일");
        }else {
            System.out.println("잘못된 이메일을 입력하셨습니다.");
        }
    }
}
