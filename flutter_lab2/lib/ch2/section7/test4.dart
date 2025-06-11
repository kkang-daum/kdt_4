class MyClass {
  final int data;
  const MyClass(this.data);
}

main(){
  var obj1 = const MyClass(10);
  var obj2 = const MyClass(10);
  print("${obj1 == obj2}");//true

  var obj3 = MyClass(10);
  var obj4 = MyClass(10);
  print("${obj3 == obj4}");//false

  var obj5 = const MyClass(20);
  print("${obj1 == obj5}");//false
}