//case 1 - singleton..................
class MyClass {
  int data = 0;

  // factory MyClass(){}//error... 객체 준비해서 리턴해야 한다..
  factory MyClass() { }
}

main(){
  //case 1 - singleton..................
  //singleton 으로 동작하는 클래스의 객체 획득을 별도의 api 로 사용하게 하고 싶지 않다..
  //이용자 입장에서는 객체를 원하는 것임으로.. 그냥.. 클래스의 객체를 생성해서 사용하게 하고 싶다.
  var obj1 = MyClass();
  var obj2 = MyClass();
  obj1.data = 10;
  obj2.data = 20;
  print("${obj1.data}, ${obj2.data}");//10, 20
}