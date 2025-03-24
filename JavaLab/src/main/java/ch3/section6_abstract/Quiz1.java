package ch3.section6_abstract;

abstract class Animal {

    protected String name;
    protected int age;

    public Animal(String name, int age){
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

class Dog extends Animal implements Printable {
    private String breed;

    public Dog(String name, int age, String breed){
        super(name, age);
        this.breed = breed;
    }

    @Override
    public void makeSound() {

    }
    public void fetch(){}

    @Override
    public void print() {

    }
}

class Cat extends Animal implements Printable {
    private String color;
    public Cat(String name, int age, String color){
        super(name, age);
        this.color = color;
    }

    @Override
    public void makeSound() {

    }
    public void climb(){}

    @Override
    public void print() {

    }
}

class Document implements Printable {
    private String title;
    private String content;

    public Document(String title, String content) {
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

    }
}

public class Quiz1 {
}
