import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      //MaterialApp 의 title 은 화면에 나오는 문자열이 아니다..
      //유저 설명 문자열..., 앱 목록에 표시되는 문자열.. 혹은 접근성을 위한 문자열..
      title: "state widget test",
      //테마설정, 라우팅처리(화면전환)
      theme: ThemeData(primarySwatch: Colors.blue),
      home: HomeScreen(),
    );
  }
}

class HomeScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('State data'),
        backgroundColor: Colors.blue,
      ),
      body: Padding(
        padding: EdgeInsets.all(16),
        child: MyUserWidget(
          name: "kim",
          email: 'a@a.com'
        ),
      ),
    );
  }
}
//부모의 데이터를 생성자 매개변수로 받는 위젯이다..
//stateful 이다.. 위젯에서 화면 구성안된다..
//자기가 받은 부모의 데이터가 자신의 state 에서 활용되어야 한다..

//위젯에서 State 를 생성하면서 State 생성자 매개변수로 전달해도 되지만..
//전달 시키지 않고 state 에서 widget 데이터 활용이 가능하다..
class MyUserWidget extends StatefulWidget {
  //StatefulWidget 이 가지는 데이터는 부모가 전달한 데이터이다..
  //이 데이터를 State 에서 이용하는데.. 아래처럼 선언하면.. State 에서 변경시킬 수도 있다..
  //부모의 데이터이다.. 부모가 관리한다.. 부모의 데이터를 자식이 참조만 하는 것이 아니라.. 변경되면..
  //데이터 구조가.. 너무 복잡해져서.. 에러 가능성 및...데이터 관리가 안된다..
  //uni directional data flow ... 단방향 데이터 흐름..
  //부모에서 자식으로 데이터가 흘려야지.. 자식에서 부모로 데이터가 흐르게 만들면 안된다..
  //가급적 부모의 데이터를 widget 에서 선언하면.. final 로 선언할 것을 권장한다..
  final String name;
  final String email;
  MyUserWidget({required this.name, required this.email});

  @override
  State<StatefulWidget> createState() {
    return MyUserWidgetState();
  }
}

class MyUserWidgetState extends State<MyUserWidget>{
  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        //State 객체내에서 widget 은 제네릭 타입으로 선언된 위젯 객체 자체를 지칭한다..
        Text("name : ${widget.name}"),
        Text("email : ${widget.email}")
      ],
    );
  }
}