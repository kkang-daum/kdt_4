package ch3.section1_oop;

class Member {
    int memberId;
    String name;
    String email;
    String password;
    String phoneNumber;

    boolean register() {
        System.out.println("register call...");
        return true;
    }
    boolean login() {
        System.out.println("login call...");
        return true;
    }
    boolean logout() {
        System.out.println("logout call..");
        return true;
    }
    String getInfo(){
        return memberId +","+name+","+email+","+password+","+phoneNumber;
    }
}

public class Quiz1 {
    public static void main(String[] args) {
        Member m1 = new Member();
        m1.name = "kim";
        m1.password = "11";
        m1.email = "a@a.com";
        m1.phoneNumber = "111";
        m1.memberId = 1;

        //하나의 형판(클래스)을 이용해 객체는 여러개 생성..
        //또다른 메모리..
        Member m2 = new Member();
        m2.name = "lee";
        m2.password = "22";
        m2.email = "b@a.com";
        m2.phoneNumber = "222";
        m2.memberId = 2;

        System.out.println(m1.getInfo());
        System.out.println(m2.getInfo());
        //1,kim,a@a.com,11,111
        //2,lee,b@a.com,22,222
    }
}
