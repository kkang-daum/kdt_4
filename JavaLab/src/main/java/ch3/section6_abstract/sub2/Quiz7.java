package ch3.section6_abstract.sub2;

public class Quiz7 {
    public static void main(String[] args) {
        Cat cat = new Cat("아기",1,"black");
        System.out.println(cat.getName());
        System.out.println(cat.getAge());
        cat.climb();
        cat.makeSound();
        cat.print();
        System.out.println();


        Dog dog = new Dog("콩이",10,"닥스훈트");
        System.out.println(dog.getName());
        System.out.println(dog.getAge());
        dog.fetch();
        dog.makeSound();
        dog.print();
        System.out.println();

        Document document = new Document("동물들","동물들에 대한 내용");
        System.out.println(document.getTitle());
        System.out.println(document.getContent());
        document.print();
    }

}

abstract class Animal {
    protected String name;
    protected int age;

    Animal(String name, int age){
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public abstract void makeSound();

}

interface Printable {
    void print();
}

class Dog extends Animal implements Printable{

    private String breed;

    Dog(String name, int age, String breed) {
        super(name, age);
        this.breed = breed;
    }

    @Override
    public void makeSound() {
        System.out.println("멍멍");
    }

    public void fetch(){
        System.out.println(name + " 공을 가져옴");
    }

    public void print() {
        System.out.println("강아지 이름: " + name + ", 나이: " + age + ", 품종: " + breed);
    }
}

class Cat extends Animal implements Printable{

    private String color;

    Cat(String name, int age, String color) {
        super(name, age);
        this.color = color;
    }

    @Override
    public void makeSound() {
        System.out.println("야옹");
    }

    public void climb(){
        System.out.println(name + " 벽을 탐");
    }

    public void print() {
        System.out.println("고양이 이름: " + name + ", 나이: " + age + ", 털 색: " + color);
    }
}

class Document implements Printable {
    private String title;
    private String content;

    Document(String title, String content){
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    @Override
    public void print() {
        System.out.println("제목: " + title + ", 내용: "+ content);
    }
}