import 'package:english_words/english_words.dart';
import 'package:flutter/material.dart';

void main(){
  runApp(MyApp());
}

//화면을 구성하기 위한 개발자의 위젯 클래스..
class MyApp extends StatelessWidget {
  //화면을 결정하기 위해서 자동 호출..
  //이 함수에서 리턴 시킨 것이 이 위젯의 화면..
  @override
  Widget build(BuildContext context) {
    final word = WordPair.random();
    //머티리얼 디자인에 입각한 다양한 기법을 사용하겠다..
    //필수는 아닌데.. 사실상의 필수..
    return MaterialApp(
      //화면 구조..
      home: Scaffold(
        appBar: AppBar(
          title: Text("Test"),
        ),
        body: Center(
          child: Text("${word.first}"),
        ),
      ),
    );
  }
}