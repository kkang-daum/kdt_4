import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return MyAppState();
  }
}

class MyAppState extends State<MyApp>{
  String? resultMessage;//native 에 일시키고.. 넘어온 결과 데이터..
  String? receiveMessage;//native 에서 먼저 채널을 통해 전달한 데이터..

  Future<Null> messageTest() async {
    //메시지 채널 준비.. native 와 동일이름으로 준비해야 한다..
    //원한다면 이름을 다르게 해서 하나의 앱에서 여러 채널을 이용해도 된다.
    var channel = BasicMessageChannel("myMessageChannel", StringCodec());
    //native 에 문자열 데이터를 전달하면서 일 시키기....
    String? result = await channel.send("Hello from Dart");
    setState(() {
      resultMessage = result;
    });

    //dart 는 가만히 있는데.. native 에서 먼저 채널로 데이터를 전달하는 경우..
    //콜백의 매개변수는 네이티브가 전달한 데이터..
    channel.setMessageHandler((String? message) async {
      setState(() {
        receiveMessage = message;
      });
      return "reply from dart";
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(title: Text("native call"),),
        body: Column(
          children: [
            Text("resultMessage : $resultMessage"),
            Text("receiveMessage $receiveMessage"),
            ElevatedButton(onPressed: messageTest, child: Text("message call")),
          ],
        ),
      ),
    );
  }
}