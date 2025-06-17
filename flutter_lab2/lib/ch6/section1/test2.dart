import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return MyAppState();
  }
}

class MyAppState extends State<MyApp>{
  String result = '';

  onPressGet() async{
    //http request header....
    Map<String, String> headers = {
      "content-type": "application/json",
      "accept": "application/json"
    };
    //network request.....
    http.Response response = await http.get(
      Uri.parse('https://jsonplaceholder.typicode.com/posts/1'),
      headers:  headers
    );
    if(response.statusCode == 200){
      setState(() {
        result = response.body;
      });
    }else {
      setState(() {
        result = "error...";
      });
    }
  }

  onPressPost() async{
    //post, put - body stream 으로 데이터 전달..
    http.Response response = await http.post(
        Uri.parse('https://jsonplaceholder.typicode.com/posts'),
        body: {'title':'hello', 'body':'world', 'userId': '1'}
    );
    if(response.statusCode == 200 || response.statusCode == 201){
      setState(() {
        result = response.body;
      });
    }else {
      setState(() {
        result = "error...";
      });
    }
  }
  onPressClient() async{
    //순차적으로.. 몇번 서버 요청이 들어가는 경우..
    var client = http.Client();
    try{
      http.Response response = await http.post(
          Uri.parse('https://jsonplaceholder.typicode.com/posts'),
          body: {'title':'hello', 'body':'world', 'userId': '1'}
      );
      if(response.statusCode == 200 || response.statusCode == 201){
        http.Response response = await http.get(
            Uri.parse('https://jsonplaceholder.typicode.com/posts/1')
        );
        if(response.statusCode == 200){
          setState(() {
            result = response.body;
          });
        }
      }else {
        setState(() {
          result = "error...";
        });
      }
    }finally {
      client.close();
    }
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(title: Text('http test'),),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Text('$result'),
              ElevatedButton(onPressed: onPressGet, child: Text('GET')),
              ElevatedButton(onPressed: onPressPost, child: Text('POST')),
              ElevatedButton(onPressed: onPressClient, child: Text('Client'))
            ],
          ),
        ),
      ),
    );
  }
}