//dart 에서는 접근제한자 제공하지 않는다..
//기본이 public 이다..
//만약 외부 파일에서 이용되지 않게 하려면.. _로.. 이름을 지정.. private 개념이 된다..
int no1 = 10;
int _no2 = 20;

void sayHello1(){}
void _sayHello2(){}

class User1 {
  String? name;
  String? _address;
}
class _User2{}

void main(){
  no1 = 100;
  _no2 = 200;//같은 파일 내에서는 _로 선언된 멤버 사용 가능..
}