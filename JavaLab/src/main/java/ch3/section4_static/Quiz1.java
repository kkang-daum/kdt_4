package ch3.section4_static;

public class Quiz1 {
    int a = 0;
    {
        System.out.println("1");
    }
    static {
        System.out.println("2");
    }
    Quiz1(){
        System.out.println("3");
    }
    Quiz1(int no){
        this();
        System.out.println("4");
    }

    public static void main(String[] args) {
        new Quiz1();
        new Quiz1(10);
    }
}

//2 -> 1 -> 3 -> 1 -> 3 -> 4
