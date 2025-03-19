package ch3.section2_method;

//call by value , call by reference

class User {
    String name;
    int score;
}

public class Exam2 {

    //매개변수 두개를 받아들여서.. 둘의 값을 바꿔치기..
    void changeValue1(int score1, int score2){
        int temp = score1;
        score1 = score2;
        score2 = temp;
    }

    void changeValue2(User obj1, User obj2){
        int temp = obj1.score;
        obj1.score = obj2.score;
        obj2.score = temp;
    }

    public static void main(String[] args) {
        User user1 = new User();
        user1.name = "kim";
        user1.score = 100;

        User user2 = new User();
        user2.name = "lee";
        user2.score = 90;

        //kim,100:lee,90
        System.out.println(user1.name+","+user1.score+":"+user2.name+","+user2.score);

        Exam2 obj = new Exam2();
        //call by value - 함수내에서 전달한 데이터가 변경이 된다고 하더라도..
        //호출한 곳에는 영향을 못준다..
        obj.changeValue1(user1.score, user2.score);
        //kim,100:lee,90
        System.out.println(user1.name+","+user1.score+":"+user2.name+","+user2.score);

        obj.changeValue2(user1, user2);
        //객체를 넘겼다.. call by reference... 객체의 주소가 넘어갔다..
        //두 함수에서 이용하는 객체가 동일하다..
        //kim,90:lee,100
        System.out.println(user1.name+","+user1.score+":"+user2.name+","+user2.score);

    }
}
