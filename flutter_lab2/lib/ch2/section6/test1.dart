class User {
  String? name;
  int? age;
  some(){
    print("name: $name, age: $age ");
  }
}

//casecade vs 메서드 체이닝...
class StringBuilder {
  String _content = "";

  StringBuilder append(String text){
    _content += text;
    return this;
  }
  void clear(){
    _content = "";
  }
  String build() => _content;
}

//중첩 구조로 casecade 이용..
class Address {
  String? street;
  String? city;
}
class Person {
  String? name;
  Address address = Address();
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

  //casecade 연산자................
  var user = User();
  user.name = "kim";
  user.age = 20;
  user.some();

  User()
    ..name = "lee"
    ..age = 30
    ..some();

  var list = []
    ..add(10)
    ..add(20)
    ..add(30);


  //casecade vs 메서드 체이닝...
  //메서드 체이닝으로 사용하고자 하는 메서드는 리턴 타입이 자기 자신만 가능..
  var result = StringBuilder()
    .append("Hello")
    .append(" ")
    .append("World")
    // .clear()//error...
    .build();

  print(result);

  var builder = StringBuilder()
    ..append("Hello")
    ..append(" ")
    ..append("World")
    ..clear();

  //중첩 casecase
  var person = Person()
    ..name = "Kim"
    ..address = (Address()
      ..street = "a street"
      ..city = "a city"
    );
}