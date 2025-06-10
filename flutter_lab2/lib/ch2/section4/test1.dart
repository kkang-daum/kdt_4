//default 초기화 지원하지 않는다..
//선언과 동시에 값을 지정하지 않으면.. null 이 대입되게 되어 있다..non-null 로 선언되어 있어서
// int a1;//error....

int a1 = 10;
int? a2 = 10;

class User {
  // int a3;//error..
  int? a4;

  testFun(){
    //local variable 에 한해서만.. non-null 타입이 선언과 동시에 초기값 지정하지 않아도 된다.
    int a5;
    //사용하기 전에는 초기화 시키고 이용해야 한다..
    a5 = 10;
  }
}

main(){
  var a1 = 10;//int
  var a2 = null;//dynamic
  var a3;//dynamic
  // var? a4 = null;//error... var 에 명시적으로 ? 을 추가할 수 없다..

  // a1 = null;//error..
  a2 = 10;
  a2 = "hello";
  a3 = 10;
  a3 = "hello";
  a3 = null;

  
}