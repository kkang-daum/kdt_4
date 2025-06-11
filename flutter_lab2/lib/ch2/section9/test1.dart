mixin MyMixin {
  int data1 = 10;
  static int data2 = 20;
  //mixin 은 생성자를 가질 수 없다..
  // MyMixin(){}//error..
  void myFun(){}
}
class MyClass with My