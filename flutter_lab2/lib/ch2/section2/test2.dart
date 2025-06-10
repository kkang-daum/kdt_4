const String data1 = "hello";
//가능하기는 하지만.. 이렇게 선언하려면.. const 로 하는 것이 좋다..
final int no1 = 10;

class User {
  //error.. 클래스의 멤버 변수는 const 로 선언 불가능하다..
  //클래스의 멤버 변수는 객체가 생성될 때마다 메모리 할당하고.. 각 메모리로 객체의
  //데이터를 상이하게 유지하기 위해서 사용..
  //아래처럼 선언하면.. 모든 객체의 메모리에.. 동일 데이터가 들어가고 값 변경도 불가능하고..
  //이걸 객체의 멤버로 선언할 이유가 있겠는가?
  //이렇게 할렴.. top level 에 선언하라..
  // const String data2 = 'hello';

  //클래스 내에 선언되는 변수를 const 로 선언하려면.. static 으로..
  static const String data2 = 'hello';

  //final 로 선언하면 선언과 동시에 값을 할당하지 않아도 된다..
  //실행되다가 할당 될 수 있다..
  //생성자 매개변수로 멤버 변수 초기화 구문..
  //객체가 여러개 생성이 된다고 하더라도.. 런타임시점의 상수 변수임으로.. 값 변경은 불가하지만..
  //각 객체별로 상이한 데이터를 가질 수도 있고..
  final int no2;
  User(this.no2);

  void some() {
    const String data3 = "hello";
    final int no3;
    no3 = 10;

    // data3 = "world";//error
    // no3 = 20;//error


    //문자열 템플릿과 상수 변수..
    int age = 20;
    //const, final, 변수 포함해서 template 작성 ==> 가능..
    String str1 = "$data1, $no1, $age";
    final String str2 = "$data1, $no1, $age"; //==>가능..
    //const 로 선언된 문자열 변수에 template 으로 값을 지정할 때 final, 일반 변수 값 지정 불가
    // const String str3 = "$data1, $no1, $age";//error...
  }


}