package ch3.section5_inherited.test6.sub;

//Bitmap 과 Builder 를 만든 개발자가 만든 패키지..
//이 Bitmap 을 이용하는 코드와 다른 패키지.

//클래스는 public 으로 선언해야 한다.. 외부 패키지에서 이용해야 한다..
//Bitmap bitmap;//은 가능하게 해야한다..
//단지 new Bitmap() 이 안되게 해야 한다..
public class Bitmap {
    public int data = 0;
    //생성자를 default 로 지정했다. 이 패키지내에서만 접근이 가능하다..
    //외부에서는 생성할 수 없다..
    Bitmap() {}
}
