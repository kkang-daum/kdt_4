class User {
  int no;
  String name;

  User(this.no, this.name);

  String sayHello(String who) => "Hello $who";
}

//클래스를 상속 관계로 이용...
class SubUser extends User {
  SubUser(super.no, super.name);
}

//클래스를 interface 로 이용..
//interface 로 이용한 클래스의 멤버가 모두 override 되어야..
class MyClass implements User {
  int no = 10;
  String name = "kim";
  @override
  String sayHello(String who) {
    return "aaa";
  }
}

//interface 를 선언하기 위한 별도의 방법을 제공하는 것보다.. 클래스를 그대로 인터페이스로 사용하게
//인터페이스로 사용하고자 설계된 클래스라면.. 주로 추상함수로만 구성할 것을 권장한다..