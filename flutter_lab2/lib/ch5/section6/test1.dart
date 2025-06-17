import 'package:flutter/material.dart';

main() => runApp(MyApp());

class User {
  String name;
  String address;
  User(this.name, this.address);
}

class OneScreen extends StatelessWidget{
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(title: Text('OneScreen'),),
        body: Container(
          color: Colors.red,
          child: Center(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Text('OneScreen', style: TextStyle(color: Colors.white, fontSize: 30),),
                ElevatedButton(
                  onPressed: () async {
                    //이동.. 되돌아 올때.. 결과 데이터를 받겠다..
                    var result = await Navigator.pushNamed(
                        context,
                        '/two',
                        arguments: {//전달할 데이터.. 여러개면 map 으로..
                          "arg1": 10,
                          "arg2": "hello",
                          "arg3": User("kim","seoul"),
                        }
                    );
                    print("result : ${(result as User).name}");
                  },
                  child: Text("Go Two"),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}

class TwoScreen extends StatelessWidget{
  @override
  Widget build(BuildContext context) {
    //화면 전환시 전달된 데이터 획득..
    Map<String, Object> args = ModalRoute.of(context)?.settings.arguments as Map<String, Object>;
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(title: Text('TwoScreen'),),
        body: Container(
          color: Colors.green,
          child: Center(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Text('TwoScreen', style: TextStyle(color: Colors.white, fontSize: 30),),
                Text("datas : ${args['arg1']}, ${args['arg2']}, ${(args['arg3'] as User).name}"),
                ElevatedButton(
                  onPressed: (){
                    Navigator.pushNamed(context, "/three");
                  },
                  child: Text("Go Three"),
                ),
                ElevatedButton(
                  onPressed: (){
                    Navigator.pop(context, User('lee', 'pusan'));
                  },
                  child: Text('pop'),
                )
              ],
            ),
          ),
        ),
      ),
    );
  }
}

class ThreeScreen extends StatelessWidget{
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(title: Text('ThreeScreen'),),
        body: Container(
          color: Colors.blue,
          child: Center(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Text('ThreeScreen', style: TextStyle(color: Colors.white, fontSize: 30),),
                ElevatedButton(
                  onPressed: (){
                    Navigator.pushNamed(context, "/four");
                  },
                  child: Text("Go Four"),
                ),
                ElevatedButton(
                  onPressed: (){
                    Navigator.pop(context);
                  },
                  child: Text('pop'),
                )
              ],
            ),
          ),
        ),
      ),
    );
  }
}

class FourScreen extends StatelessWidget{
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(title: Text('FourScreen'),),
        body: Container(
          color: Colors.yellow,
          child: Center(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Text('FourScreen', style: TextStyle(color: Colors.white, fontSize: 30),),
                ElevatedButton(
                  onPressed: (){
                    Navigator.pop(context);
                  },
                  child: Text('pop'),
                )
              ],
            ),
          ),
        ),
      ),
    );
  }
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      initialRoute: '/one',
      routes: {
        "/one": (context) => OneScreen(),
        "/two": (context) => TwoScreen(),
      },
      //어디선가 pushNamed() 함수가 호출될때 자동 실행..
      //매개변수가.. 요청 정보이다.. 이름, argument
      onGenerateRoute: (settings){
        if(settings.name == '/three'){
          //리턴시키지 않으면.. 화면 전환 처리가 안된다..
          //동적으로.. 다른 widget 을 리턴시켜도 된다..
          return MaterialPageRoute(
            builder: (context) => ThreeScreen(),
            //요청정보를 추가하지 않으면 argument 가 전달 안된다..
            settings: settings
          );
        }else if(settings.name == '/four'){
          return MaterialPageRoute(
              builder: (context) => FourScreen(),
              settings: settings
          );
        }
      },
    );
  }
}