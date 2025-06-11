class User {
  late String name;
  late int age;

  //멤버 초기화 case 1.....
  // User(String name, int age){
  //   this.name = name;
  //   this.age = age;
  // }

  //case 2
  //생성자에 한해서..  body 내용이 없다면 {} 생략 가능..
  //매개변수를 그대로.. 멤버 변수에 대입하는 경우..
  // User(this.name, this.age);

  //case 3
  //초기화 목록에서 멤버 초기화...
  // User(String name, int age): this.name = name, this.age = age;

  //case 4
  // User(List<dynamic> args): this.name = args[0], this.age = args[1];

  static int calFun(int arg){
    return arg * 10;
  }
  User(String name, int age): this.name = name, this.age = calFun(age);
}