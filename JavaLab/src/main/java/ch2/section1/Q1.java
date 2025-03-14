package ch2.section1;

public class Q1 {
    public static void main(String[] args) {
        System.out.println("[교환전]");
        int javaScore = 89;
        int pythonScore = 91;
        System.out.println("javaScore : "+javaScore);
        System.out.println("pythonScore : "+pythonScore);
        System.out.println("----------------------");
        System.out.println("[교환후]");
        int temp = javaScore;
        javaScore = pythonScore;
        pythonScore = temp;
        System.out.println("javaScore : "+javaScore);
        System.out.println("pythonScore : "+pythonScore);
    }
}
