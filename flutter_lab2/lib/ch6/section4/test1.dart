import 'package:flutter/material.dart';

main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  //B/L 함수..
  int calFun(int x){
    return x * x;
  }
  
  Stream<int> test() {
    Duration duration = Duration(seconds: 3);
    Stream<int> stream = Stream<int>.periodic(duration, calFun);
    return stream.take(5);
  }
  
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(title: Text('stream test'),),
        body: Center(
          child: StreamBuilder(
              stream: test(), 
              builder: (context, snapshot){
                if(snapshot.connectionState == ConnectionState.done){
                  //데이터 발행이 완료된 상태..
                  return Center(
                    child: Text("Completed"),
                  );
                }else if(snapshot.connectionState == ConnectionState.waiting){
                  return Center(
                    child: SizedBox(
                      width: 100,
                      height: 100,
                      child: CircularProgressIndicator(),
                    ),
                  );
                }
                return Center(
                  child: Text("data : ${snapshot.data}"),
                );
              },
          ),
        ),
      ),
    );
  }
}