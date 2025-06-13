import 'package:flutter/material.dart';
import 'package:flutter_lab2/ch4/section5/widgets/header_widget.dart';
import 'package:flutter_lab2/ch4/section5/widgets/icon_widget.dart';
import 'package:flutter_lab2/ch4/section5/widgets/image_widget.dart';

main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(title: Text('layout test'),),
        body: Column(
          children: [
            HeaderWidget(),
            ImageWidget(),
            IconWidget(),
          ],
        ),
      ),
    );
  }
}