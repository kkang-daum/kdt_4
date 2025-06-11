mixin MyMixin {
  int data1 = 10;
  static int data2 = 20;
  //mixin 은 생성자를 가질 수 없다..
  // MyMixin(){}//error..
  void myFun(){}
}

class MyClass with MyMixin {
  void sayHello(){
    print("data : ${data1}");
    myFun();
  }
}

//dart 3.0 버전부터...
mixin class MyMixin2 {
  int data = 10;
  void sayHello(){}
  MyMixin2();//생성자 추가도 된다.. mixin 이지만.. mixin class 이다.. 즉 클래스이다..
}

//클래스에서 with 로 이용 가능...
class SomeClass with MyMixin2 {
  void a(){
    data++;
    sayHello();
  }
}
//mixin class 를 상속으로 다른 클래스를 만들 수도 있고..
class SomeClass2 extends MyMixin2 {
  void a(){
    data++;
    sayHello();
  }
}

main(){
  //mixin 을 직접 생성할 수 없다.. 생성자 추가도 안된다..
  // var obj = MyMixin();//error...
  MyMixin.data2++;

  var obj = MyMixin2();//객체 생성도 가능하고..
}