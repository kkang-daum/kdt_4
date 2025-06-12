import 'package:flutter/material.dart';

class MyColorWidget1 extends StatefulWidget {
  Color color;
  MyColorWidget1(this.color);
  @override
  State<StatefulWidget> createState() {
    return MyColorWidgetState1(color);
  }
}

class MyColorWidgetState1 extends State<MyColorWidget1> {
  Color color;
  MyColorWidgetState1(this.color);
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