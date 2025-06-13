import 'package:flutter/material.dart';
import 'package:flutter_lab2/ch4/section5/widgets/content_widget.dart';
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
        //flutter에서 화면을 구성하다가.. 화면이 가로 세로 방향으로 벗어나 버리면..
        //자동 스크롤 지원하지 않는다.. 검정색 노랑색 패턴, 경고 패턴이 뜬다..
        //스크롤 준비하지 않았다는 워닝이다..
        //동시에 여러개의 위젯을 한꺼번에 스크롤 해야 한다면 ==> ListView
        //단일 위젯을 스크롤 해야 한다면.. ==> SingleChildScrollView
        body: SingleChildScrollView(
          child: Column(
            children: [
              HeaderWidget(),
              ImageWidget(),
              IconWidget(),
              ContentWidget(),
            ],
          ),
        ),
      ),
    );
  }
}