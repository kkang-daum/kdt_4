import 'package:flutter/material.dart';

main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  //리턴 타입 Future...
  Future<int> funA() {
    return Future.delayed(Duration(seconds: 3), (){
      return 10;
    });
  }
  Future<int> funB(int arg){
    return Future.delayed(Duration(seconds: 2), (){
      return arg * arg;
    });
  }
  Future<int> calFun() async {
    // funA().then((value){
    //
    // });
    int aData = await funA();
    int bData = await funB(aData);

    return bData;
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(title: Text('Future Test'),),
        body: Center(
          child: FutureBuilder(
            future: calFun(),
            builder: (context, snapshot){
              if(snapshot.hasData){
                return Center(
                  child: Text('${snapshot.data}'),
                );
              }
              return Center(
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    SizedBox(
                      width: 100,
                      height: 100,
                      child: CircularProgressIndicator(),
                    ),
                    Text('waiting....'),
                  ],
                ),
              );
            },
          ),
        ),
      ),
    );
  }
}