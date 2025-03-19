package ch3.section1_oop.sub1;

class Member {
    int memberId;
    String name, email, password, phoneNumber;

    Member(int memberId, String name, String email, String password, String phoneNumber) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    boolean register() {
        System.out.println("register 함수 호출");
        return true;
    }

    boolean login() {
        System.out.println("login 함수 호출");
        return true;
    }

    boolean logout() {
        System.out.println("logout 함수 호출");
        return true;
    }

    String getInfo() {
        return "memberId : "+memberId+"\nname : "+name+"\nemail: "+email+"\npassword : "+password+"\nphoneNumber : "+phoneNumber;
    }
}

public class Quiz1 {
    public static void main(String[] args) {
        Member member1 = new Member(1, "홍길동", "hong01@email.com", "abcd1234", "010-1111-1111\n");
        Member member2 = new Member(2,"홍길서", "hong02@email.com", "1234abcd", "010-2222-2222\n");

        member1.register();
        member1.login();
        member1.logout();
        System.out.println(member1.getInfo());

        member2.register();
        member2.login();
        member2.logout();
        System.out.println(member2.getInfo());
    }
}
