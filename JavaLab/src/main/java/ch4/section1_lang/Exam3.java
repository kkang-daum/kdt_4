package ch4.section1_lang;

public class Exam3 {
    public static void main(String[] args) {
        //기초 타입의 데이터가 객체로 이용해야 하는 경우..
        Object[] objArray = {
          new Integer(10),
          new Boolean(true),
          new Double(10.0)
        };

        //원래는 위처럼 사용해야 하는데..
        //편의성을 위해서 마치 값을 직접 준것처럼 사용할 수 있다..
        //내부적으로는 객체가 만들어져서 들어간다..
        Object[] objArray2 = {
                10, true, 10.0
        };

        int score1 = 60;
        //정상적인 박싱... 기초 타입을 객체로 감쌌다는 의미에서 용어로 박싱이라고..
        Integer wrapper1 = new Integer(score1);
        Integer wrapper2 = Integer.valueOf(score1);
        //박싱된 것을 다시 원 데이터로.. 언박싱..
        int a1 = wrapper1.intValue();

        //편의성을 위해서 제공..
        Integer b1 = score1;
        int c1 = b1;
    }
}
