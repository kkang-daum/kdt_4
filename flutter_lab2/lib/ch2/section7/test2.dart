class MyClass {
  int data;
  MyClass(this.data){}//기본(클래스명과 동일한) 생성자..
  MyClass.one(this.data){}//named constructor..
  MyClass.two(this.data){}
}

class MyClass2 {
  int data1 = 10;
  MyClass2(int data1, int data2){
    print("MyClass2 call...");
  }
  //다른 생성자를 호출하는 생성자는 body 를 가질 수 없다..
  // MyClass2.one(int arg): this(arg, 0) { }//error...
  //초기화 목록에는.. 멤버 초기화, 다른 생성자 호출구문이 위치하는데..
  //둘을 같이 사용할 수 없다..
  // MyClass2.one(int arg): this(arg, 0), this.data1 = arg;//error..
  MyClass2.one(int arg): this(arg, 0);
  MyClass2.two(): this.one(0);
}

main(){
  var obj1 = MyClass(10);
  var obj2 = MyClass.one(20);
  var obj3 = MyClass.two(30);

  print("${obj1.data}, ${obj2.data}, ${obj3.data}");//10, 20, 30
}