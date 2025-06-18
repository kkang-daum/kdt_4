import 'package:flutter/material.dart';
import 'package:flutter_lab2/ch1/test1.dart';
import 'package:provider/provider.dart';


//provider 로 자손에게 공개하고자 하는 개발자 클래스..
//데이터 변경시마다.. 자손을 update 시키기 위해서.. ChangeNotifier
class Counter with ChangeNotifier {
  int _count = 0;

  int get count => _count;

  void increment() {
    _count++;
    //데이터 변경후에.. 자손 update 명령 내려야 한다..
    notifyListeners();
  }
}

//provider 로 공개하고자 하는 데이터.. 개발자 클래스..
class Sum {
  int _sum = 0;

  int get sum => _sum;

  void set sum(value) {
    _sum = 0;
    for (int i = 1; i <= value; i++) {
      _sum += i;
    }
  }

  //다른 데이터를 참조...
  Sum(Counter counter) {
    sum = counter.count;
  }
}

main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(title: Text("provider test"),),
        body: MultiProvider(
          providers: [
            Provider<int>.value(value: 10,),
            Provider<String>.value(value: "hello",),
            ChangeNotifierProvider<Counter>.value(value: Counter(),),
            ProxyProvider<Counter, Sum>(
              //model - 첫번째 제네릭 타입의 객체.. 즉 다른 provider 가 만든 객체
              //sum - 이전 update 호출시에 이곳에서 리턴시킨, 즉 내가 공개한 객체.
              update: (context, model, sum) {
                if (sum != null) {
                  sum.sum = model.count;
                  return sum;
                } else {
                  return Sum(model);
                }
              },
            )
          ],
          child: SubWidget(),
        ),
      ),
    );
  }
}

class SubWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    //조상에서 공개한 provider data 중, 자신이 필요한 data 획득..
    var counter = Provider.of<Counter>(context);
    var intData = Provider.of<int>(context);
    var strData = Provider.of<String>(context);
    var sum = Provider.of<Sum>(context);

    return Container(
      color: Colors.orange,
      child: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text("intData : $intData"),
            Text("strData : $strData"),
            Text("sum : ${sum.sum}"),
            Text("counter : ${counter.count}"),
            ElevatedButton(onPressed: () {
              counter.increment();
            },
              child: Text('increment'),
            )
          ],
        ),
      ),
    );
  }
}