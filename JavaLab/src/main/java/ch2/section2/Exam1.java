package ch2.section2;

public class Exam1 {
    public static void main(String[] args) {
        System.out.println("10 % 4 : " + (10 % 4));//10 % 4 : 2
        //수학에서는 결과가 실수가 되지만.. 연산 대상들이 정수여서 결과도 정수로
        //소수점 버려버린다..
        System.out.println("10 / 4 : " + (10 / 4));//10 / 4 : 2
        //나누기 연산에 의한 소수점까지 결과로 필요하다면.. double 타입으로 만들고
        //연산을 진행해야 한다..
        //아래의 경우는 실수로 변형된 값이 나오기는 하지만..
        //정수로 결과가 나온후에.. 그 데이터가 실수로 변형되어서.. 소수점 데이터 사라진
        System.out.println("10 / 4 : " + (double)(10 / 4));//10 / 4 : 2.0
        //나누기 연산이 진행되기 전에 실수로 바꾸어서.. 결과가 실수로 나오게..
        System.out.println("10 / 4 : " + ((double)10 / 4));//10 / 4 : 2.5

        //복합 할당 연산자...
        int data1 = 10;
        int result = data1 + 10;//복합할당 연산자 사용하면 안되는 경우..

        //자신의 데이터를 연산한 후 결과를 자신에게 할당하는 경우..
        data1 = data1 + 10;//ok....
        data1 += 10;
        data1 *= 10;
    }
}
