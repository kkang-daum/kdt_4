package ch3.section1_oop.sub2;

import java.util.UUID;

class Member {
    int memberId;
    String name;
    String email;
    String password;
    String phoneNumber;

    Member() {
        memberId = UUID.randomUUID().hashCode();
    }

    Member(int id) {
        memberId = id;
    }

    boolean register() {
        this.name = "base";
        this.email = "base@com";
        this.password = "secret";
        this.phoneNumber = "010-0000-0000";

        System.out.println("register() 함수가 호출되었습니다.");
        return true;
    }

    boolean register(String name, String email, String password, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;

        System.out.println("register() 함수가 호출되었습니다.");
        return true;
    }

    boolean login() {
        System.out.println("login() 함수가 호출되었습니다.");
        return true;
    }

    boolean logout() {
        System.out.println("logout() 함수가 호출되었습니다.");
        return true;
    }

    String getInfo() {
        return "memberId: " + memberId +
                "\nname: " + name +
                "\nemail: " + email +
                "\npassword: " + password +
                "\nphoneNumber: " + phoneNumber;

    }
}

public class Quiz1 {
    public static void main(String[] args) {
        Member member1 = new Member();
        Member member2 = new Member(1);

        member1.register();
        member2.register("김자바", "java@com", "java", "010-0000-0001");
        System.out.println();

        member1.login();
        member2.login();
        System.out.println();

        member1.logout();
        member2.logout();
        System.out.println();

        System.out.println(member1.getInfo());
        System.out.println();
        System.out.println(member2.getInfo());
    }
}
