import 'package:flutter/material.dart';
import 'package:flutter_lab2/ch3/section3/my_color_widget1.dart';
import 'package:flutter_lab2/ch3/section3/my_color_widget2.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: MyListWidget(),
    );
  }
}

class MyListWidget extends StatefulWidget{
  @override
  State<StatefulWidget> createState() {
    return MyListWidgetState();
  }
}
class MyListWidgetState extends State<MyListWidget>{
  //화면에 뿌리는 위젯들...
  List<Widget> widgets = [
    //test1.....
    //서로 다른 타입의 StatefulWidget 인 경우...
    //다시 생성되는 위젯 객체에 state 객체 매핑에 문제가 없다..
    //타입으로 식별하기 때문이다..
    // MyColorWidget1(Colors.red),
    // MyColorWidget2(Colors.blue),

    //test2.....
    //동일 타입의 위젯을 여러개 사용하는 경우..
    //타입으로.. 위젯을 식별할 수 없는 경우..
    //순서로 매핑한다.. 원하지 않게 될 수 있다..
    // MyColorWidget1(Colors.red),
    // MyColorWidget1(Colors.blue),

    //test3... 타입, 순서에 의해 위젯 매핑에 문제가 발생하면..
    //key 로 식별하게..
    MyColorWidget1(Colors.red, key: UniqueKey(),),
    MyColorWidget1(Colors.blue, key: UniqueKey(),),
  ];

  onChange(){
    setState(() {
      widgets.insert(1, widgets.removeAt(0));
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("key test"),),
      body: Column(
        children: [
          Row(children: widgets,),
          ElevatedButton(onPressed: onChange, child: Text("toggle"),),
        ],
      ),
    );
  }
}