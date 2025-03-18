package ch2.section3;

//배열 변수 선언, 이용 방법 테스트.......
public class Exam1 {
    public static void main(String[] args) {
        //배열 변수 선언 방법 1
        int score = 30;//변수의 메모리에 실제 데이터.. primitive type
        //배열 변수를 선언하고 초기화..
        //초기화는 실제 데이터를 담는 것이 아니라.. 데이터를 담을 수 있는
        //메모리 확보..
        //배열변수는 꼭 사이즈로 초기화(메모리확보)하고 사용해야
        //배열의 사이즈는 불변...
        //배열 변수에 저장된 것은 실제 데이터가 아니라.. 확보한 메모리를 지칭하는 주소값
        //주소값을 가지는 변수를 reference 변수..
        int[] scores = new int[5];
        int scores1[] = new int[5];

        System.out.println("primitive type : " + score);//primitive type : 30
        System.out.println("reference type : " + scores);//reference type : [I@46f5f779

        //배열에 데이터를 추가하거나... 획득하거나..
        scores[0] = 10;
        scores[1] = 20;
        System.out.println(scores[0]);//10
        System.out.println(scores[1]);//20
        System.out.println(scores[2]);//0

        //Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5
        //배열의 사이즈를 벗어난 index 를 지칭했을때의 에러..
        //0부터 시작해서 ~ 4
//        scores[5] = 10;
//        scores[3] = "hello";//error...

        //배열 선언 방법 2... 위의 축약형...
        //배열을 선언하는 순간.. 배열에 담길 데이터가 있다면...
        //사이즈 + 데이터 저장
        //배열 사이즈 3로 고정..
        int[] score2 = {10, 20, 30};

        int[] score3;
//        scores = {10, 20, 30};//선언과 동시에.. 데이터를 지정해야 가능..
        score3 = new int[3];
        score3[0] = 10;


    }
}
