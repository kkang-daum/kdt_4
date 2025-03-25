package ch3.section8_exception;

//은행 시스템을 가정...
//예금 인출, 입금이 진행된다..
//입금이 0보다 작은 수가 들어온다..
//출금이 잔고보다 큰 수가 들어온다..
//이런 상황은 java 적으로는 예외 상황은 아니다..
//이런 상황을 true, false 등으로.. 적절하게 처리할 수도 있기는 하지만..
//이 상황을 앱에서 존재할 수 없는 상황으로 보고 custom exception 으로 처리해서
//예외처리 코드에의해 코드가 개발되게 하고 싶다..

import kotlin.io.encoding.Base64Kt;

class BadBankingException extends Exception {
    //복잡하게 작성할 수도 있지만..
    //어떤 에러인지를 클래스명(type)으로 구분할 용도이지..
    //에러 발생에 대응하는 곳은 다른곳에서..
    //거의 대부분 아래처럼..
    BadBankingException(String s){
        super(s);
    }
}

//통장을 추상화 시킨 클래스..
class BankAccount {
    private String name;
    private int number;//통장 번호
    private int balance;//잔고...

    BankAccount(String name, int number, int balance){
        this.name = name;
        this.number = number;
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }
    //출금 요청..
    void withdraw(int amt) throws BadBankingException{
        if(amt > balance){
            throw new BadBankingException("잔액 부족");
        }
        balance = balance - amt;
    }
    void deposit(int amt) throws BadBankingException {
        if(amt < 0){
            throw new BadBankingException("0보다 작은 금액 입금 요청");
        }
        balance = balance + amt;
    }
}
public class Exam3 {
    public static void main(String[] args) {
        BankAccount myAccount = new BankAccount("홍길동", 1, 1000);
        try{
            myAccount.withdraw(3000);
        }catch (BadBankingException e){
            System.out.println(e);
        }
    }
}
