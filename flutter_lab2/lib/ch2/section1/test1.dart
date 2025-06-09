//외부 파일 import.. 상대경로로...
// import 'outer.dart';
//절대경로로...
//lib 폴더 다음부터.. 지정..
// import '/ch2/section1/outer.dart';
//package 를 이용한 절대 경로 명시.. lib 부터..
import 'package:flutter_lab2/ch2/section1/outer.dart';

//entry point.. 함수명이 중요하다.. 리턴 타입은 의미 없다..
//생략하면 dynamic
main(){
  no1 = 100;
  // _no2 = 200;//error..
  sayHello1();
  // _sayHello2();//error

  //객체 생성 구분..
  //new 사용해도 되고.. 생략해도 되고..
  User1 obj1 = User1();
  obj1.name = "kim";
  // obj1._address = "a";//error..
}