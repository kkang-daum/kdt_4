package ch3.section5_inherited.test2;

class Super{
    int a;
    { System.out.println("1");}
    static {System.out.println("2");}
    Super(){
        System.out.println("3");
    }
}
class Sub extends Super {
    int b;
    { System.out.println("4");}
    static { System.out.println("5");}
    Sub(){
        System.out.println("6");
    }
    Sub(int a){
        this();
        System.out.println("7");
    }
}
public class Quiz1 {
    public static void main(String[] args) {
        new Sub();
        new Sub(10);
    }
}
//2 5 1 3 4 6 1 3 4 6 7