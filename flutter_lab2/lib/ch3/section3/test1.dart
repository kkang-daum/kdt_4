import 'package:flutter/material.dart';

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
    
  ];
}