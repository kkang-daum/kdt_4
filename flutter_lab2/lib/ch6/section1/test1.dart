import 'dart:convert';

import 'package:flutter/material.dart';

main() => runApp(MyApp());

//json 데이터를 추상화 시킨 클래스라는 가정..
//json -> 객체
//객체 -> json
class Todo {
  int id;
  String title;
  bool completed;
  Todo(this.id, this.title, this.completed);

  //json parsing.. 결과가 Map(여기까지는 dart api 가 해준다..)
  //Map 데이터를 이용해 객체를 생성해.. 객체의 변수에 map 데이터를 매핑할 수 있는 생성자를 만들어라
  //생성자 이름은 임의 이름이지만 fromJson 으로 할 것을 권장..
  Todo.fromJson(Map<String, dynamic> json):
      id = json["id"],
      title = json["title"],
      completed = json["completed"];

  //객체의 데이터를 json 문자열로 만드는 함수를 직접 준비하라..
  //이 함수의 이름은 꼭 toJson() 이어야 한다..
  Map<String, dynamic> toJson() =>
      {"id": id, "title": title, "completed": completed};
}

class MyApp extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return MyAppState();
  }
}

class MyAppState extends State<MyApp>{
  //어디에선가 구한 json 문자열.. 파싱하기 전까지는 json 은 단순 문자열이다..
  String jsonStr = '{"id": 1, "title":"hello", "completed": false}';

  Todo? todo;
  String result = '';

  onPressDecode(){
    //json 문자열 파싱해서.. DTO 객체에 담아서 이용..
    //파싱..
    Map<String, dynamic> map = jsonDecode(jsonStr);
    //map -> dto 객체로..
    todo = Todo.fromJson(map);
    setState(() {
      result = "decode : ${todo?.id}, ${todo?.title}, ${todo?.completed}";
    });
  }
  onPressEncode(){
    //객체를.. json 문자열로... 
    setState(() {
      result = "encode : ${jsonEncode(todo)}";
    });
  }
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(title: Text("json test"),),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Text('$result'),
              ElevatedButton(onPressed: onPressDecode, child: Text('decode')),
              ElevatedButton(onPressed: onPressEncode, child: Text('encode'))
            ],
          ),
        ),
      ),
    );
  }
}