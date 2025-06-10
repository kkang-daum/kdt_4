void some(int arg1, [String? arg2, bool arg3 = false]) { }

void myFun1(Function argFun){ }
void myFun2(int argFun(int a)){ }

//setter/getter.........
String _name = "Kim";
String get name {
  return _name.toUpperCase();
}
set name(value){
  _name = value;
}

main(){
  some(10);//ok....
  some(10, "hello");//ok...
  some(20, "hello", true);//ok...
  // some(10, true);//error.. 순서에 의해 호출되기 때문에.. 타입 문제..
  // some(10, arg2: "hello", arg3: true);//name 을 명시할 수 없다..

  myFun1((){});
  myFun1((int a1, int a2){});

  // myFun2((){});//error.. 타입이 맞지 않는다..
  myFun2((int a){
    return a * 10;
  });

  name = "lee";
  print("name: $name");//name: LEE
}