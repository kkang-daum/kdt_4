import 'package:flutter/material.dart';

main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(title: Text("text field test")),
        body: TextScreen(),
      ),
    );
  }
}

class TextScreen extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return TextScreenState();
  }
}

class TextScreenState extends State<TextScreen> {
  //TextField 하나당 하나씩 준비..
  //TextField 의 입력 순간의 이벤트 처리 가능하고..
  //TextField 에 유저가 입력한 값을 유지..
  var controller = TextEditingController();
  int textCounter = 0;

  printValue() {
    print("${controller.text}");
    setState(() {
      textCounter = controller.text.length;
    });
  }

  @override
  void initState() {
    super.initState();
    controller.addListener(printValue);
  }

  @override
  void dispose() {
    super.dispose();
    controller.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return TextField(
      style: TextStyle(fontSize: 15.0),
      controller: controller,
      decoration: InputDecoration(
        labelText: "Name",
        prefixIcon: Icon(Icons.input),
        border: OutlineInputBorder(),
        hintText: "hint text",
        helperText: "데이터를 입력하세요..",
        counterText: "$textCounter characters",
      ),
      textInputAction: TextInputAction.search,
      keyboardType: TextInputType.emailAddress,
      minLines: 3,
      maxLines: 3,
    );
  }
}
