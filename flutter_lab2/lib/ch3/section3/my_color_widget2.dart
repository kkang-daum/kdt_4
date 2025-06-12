import 'package:flutter/material.dart';

class MyColorWidget2 extends StatefulWidget {
  Color color;
  MyColorWidget2(this.color);
  @override
  State<StatefulWidget> createState() {
    return MyColorWidgetState2(color);
  }
}

class MyColorWidgetState2 extends State<MyColorWidget2> {
  Color color;
  MyColorWidgetState2(this.color);
  @override
  Widget build(BuildContext context) {
    return Expanded(
      child: Container(
        color: color,
        width: 150,
        height: 150,
      ),
    );
  }
}