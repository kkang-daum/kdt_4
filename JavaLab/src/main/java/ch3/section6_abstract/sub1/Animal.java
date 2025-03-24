package ch3.section6_abstract.sub1;

// + public , - private , #protected /이태릭체는 추상형이다.
public abstract class Animal {

    protected String name;
    protected int age;

    public Animal(String name, int age){

    }
    public String getName(String name) { return name;}
    public int getName(int age) { return age;}

    //추상
    public abstract void makeSound();

}

interface printable{
    public void print();
}

 class Dog extends Animal implements printable{

    private String bread;

    public Dog(String name, int age, String bread){
        super(name,age); // 추상클래스에서는 생성자는 가능
    }
    public void fetch(){}

    public void makeSound(){System.out.println("자식클래스(Dog)에서 추상화를 실행");}
    public void print(){System.out.println("Dog에서 구현한 인터페이스입니다.");}

}

class Cat extends Animal implements printable{

    private String color;

    public Cat(String name, int age, String color){
        super(name,age);
    }

    public void climb(){}

    public void makeSound(){System.out.println("자식클래스(Cat)에서 추상화를 실행");}
    public void print(){System.out.println("Cat에서 구현한 인터페이스입니다.");}

}
class Document implements printable{ //상속 X

    private String title;
    private String content;

    public Document(String title , String content){}

    public String getTitle(String title){return title;}
    public String getContent(String content){return content;}

    public void print(){System.out.println("Document에서 구현한 인터페이스입니다.");}



}