package ch3.section5_inherited.test6;


//builder pattern...
//객체 생성시점에 직접 생성하지 못하고..
//객체 생성 대행자(Builder)를 이용해서 생성하게 하는 패턴...
//객체 생성 작업이 복잡하거나.. 다양한 조건에 의해 객체가 생성되어야 할때..
//그 복잡함을 builder에게 위임시키겠다는 의도...

//우리가 생성해서 이용해야 하는 클래스는 Bitmap 이다..
//이 Bitmap 을 대신 생성해주는 Builder 를 이용해서 생성하게 하고자 한다..


import ch3.section5_inherited.test6.sub.Bitmap;
import ch3.section5_inherited.test6.sub.Builder;

public class Exam4 {
    public static void main(String[] args) {
//        Bitmap bitmap = new Bitmap();//error
        Builder builder = new Builder();
        Bitmap bitmap = builder.createBitmap();
        Bitmap bitmap1 = builder.createBitmap();
    }
}
