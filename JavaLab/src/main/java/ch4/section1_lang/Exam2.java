package ch4.section1_lang;

public class Exam2 {
    public static void main(String[] args) {
        //string equals.....................
        String str1 = "hello";
        String str2 = "hello";
        String str3 = new String("hello");
        String str4 = new String("hello");

        //기초 타입의 == 은 값 비교이다..
        //객체 타입의 == 은 객체비교이다..
        //new 로 정식으로 객체를 생성하면 메모리 할당해준다..
        //String 을 마치 기초 타입처럼 사용하면... 객체를 생성하기는 하는데..
        //이후 동일한 테이터로 다시 선언하면 그 데이터를 가지는 객체가 이미 있는지
        //확인한 후 있으면.. 그 메모리 그대로 이용, 없으면 생성..
        System.out.println(str1 == str2);//true
        System.out.println(str1 == str3);//false
        System.out.println(str3 == str4);//false

        //Object 의 equals 는 주소값 비교이다..
        //String 에서 오버라이드해서 String 의 equals 는 값 비교이다..
        //String 의 값 비교할 때는 == 사용하면 신경써야 한다..
        //그냥 equals 사용하자..
        System.out.println(str1.equals(str2));//true
        System.out.println(str1.equals(str3));//true
        System.out.println(str3.equals(str4));//true

        System.out.println(str1.hashCode());//99162322
        System.out.println(str2.hashCode());//99162322

        //String 은 불변이다.. 값이 바뀌면 매번 매모리 재 할당이다..
        str1 += "world";
        System.out.println(str1.hashCode());//-1524582912

        //StringBuffer는 동일 객체의 값이 변경된다..
        //문자열이 빈번하게 변경되는 경우에는 StringBuffer가 효율적이다..
        StringBuffer sb = new StringBuffer("hello");
        System.out.println(sb.hashCode());//184966243
        sb = sb.append("world");
        System.out.println(sb.hashCode());//184966243


        //String 의 함수들...
        String str = "Hello World";
        System.out.println(str.equalsIgnoreCase("hello world"));//true

        //문자열 연결 함수..
        System.out.println(str.concat(" aaa"));//Hello World aaa

        System.out.println(str.replace('o', 'O'));//HellO WOrld

        //특정 위치의 문자열 추출..
        //1 부터 뒤로 모두 다..
        System.out.println(str.substring(1));//ello World
        System.out.println(str.substring(1, 5));//ello

        //구분자를 주고.. 구분자로 문자열을 잘라서 배열로 리턴..
        String[] words = str.split(" ");
        for(String word : words){
            System.out.println(word);
        }

        String[] address = {
                "서울시 강남구 역삼동",
                "서울시 송파구 방이동",
                "서울시 강남구 청담동"
        };
        //강남구 주소만..
        //강남구의 문자열 위치를 획득해서 -1 이 아니면 있다는 이야기..
        for(int i = 0; i < address.length; i++){
            if(address[i].indexOf("강남구") != -1){
                System.out.println(address[i]);
            }
        }

        
    }
}
