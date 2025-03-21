package ch3.section5_inherited.test5;

import org.w3c.dom.css.ElementCSSInlineStyle;

class Singleton {
    int a = 0;

    //객체 생성이 하나만 되게...
    //생성..생성자..
    //외부에서 생성자를 접근하면.. 마음대로 몇번이고..생성..
    //내부에서 생성하지 않는한 외부에서는 생성할 수 없다..
    private Singleton(){}

    //외부에서 이 객체를 이용하기는 해야 한다..
    //객체 생성하지 않고 사용할 수 있는 구성요소.. static
    private static Singleton instance;

    //외부에서 객체를 획득할 수 있는 함수를 제공한다.. static 으로..
    public static Singleton getInstance(){
        if(instance == null){
            //이 함수가 호출된 시점에.. 객체 생성이 안되어 있다면..
            //생성
            instance = new Singleton();
        }
        return instance;
    }
}

public class Quiz3 {
    public static void main(String[] args) {
//        Singleton s1 = new Singleton();
//        s1.a = 10;
//        Singleton s2 = new Singleton();
//        s2.a = 20;
//        System.out.println(s1.a+","+s2.a);//10,20

        Singleton obj1 = Singleton.getInstance();
        Singleton obj2 = Singleton.getInstance();
        obj1.a = 10;
        obj2.a = 20;
        //만약 객체가 여러개 생성이 되었다면 obj1과 obj2 는 다를 것이고..
        //하나만 생성되었다면 동일 값일 것이고..
        System.out.println(obj1.a+","+obj2.a);//20,20
    }
}
