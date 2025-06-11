class User {
  String? name;
  int? age;
  some(){
    print("name: $name, age: $age ");
  }
}

main(){
  int a = 8;
  print('${a / 5}');//1.6
  print('${a ~/ 5}');//1

  //dart 의 최상위 타입..
  Object obj = User();
  // obj.some();//error..
  if(obj is User){//is 에 의해 true 가 나오면 smart casting...
    obj.some();//ok...
  }
  (obj as User).some();
}