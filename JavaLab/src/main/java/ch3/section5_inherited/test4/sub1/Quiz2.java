package ch3.section5_inherited.test4.sub1;

class User {  // public ?
    private String name;
    private int age;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}

public class Quiz2 {

    public static void main(String[] args) {
        User user1 = new User();

        user1.setName("userName1");
        user1.setAge(22);

        System.out.println(user1.getName() + "'s age : " + user1.getAge() );
    }
}
