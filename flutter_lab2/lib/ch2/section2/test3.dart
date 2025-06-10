class User {}

main() {
  dynamic data = 10;
  data = "hello";
  data = true;
  data = User();

  //var 은 타입 유추 기법이다.. 선언하는 라인에서 대입되는 값으로 타입이 고정된다..
  var no = 10;
  // no = true;//error
  // no = "hello";//error

  //var 은 타입 유추 기법이다.. 선언과 동시에 값 대입이 없다면?
  //dynamic 으로 유추가 된다..
  var no1;
  no1 = 10;
  no1 = 'hello';
  no1 = true;
}