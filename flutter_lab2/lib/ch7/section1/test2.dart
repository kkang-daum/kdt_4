import 'package:flutter/material.dart';

main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(title: Text('inherited test'),),
        body: MyInheritedWidget(TestWidget()),
      ),
    );
  }
}

//공통의 조상 역할을 하기 위한.. 위젯..
//데이터만.. 데이터 변경할 함수만..
class MyInheritedWidget extends InheritedWidget {
  int count = 0;//자손들이 이용할 상태 데이터 가정..

  //나를 생성하면서 매개변수에 어떤 위젯을 전달할 지 모르겠지만.. 나는 그 위젯의 부모다..
  //자식 API 와 결합되지 않는다..
  MyInheritedWidget(child): super(child: child);

  //자손 누군가가.. 상태 값을 변경하기 위해서 호출하는 함수..
  increment(){
    count++;
  }
  //InheritedWidget 의 상태 변수가 변경이 되면.. 하위 자식에 변경된 데이터가 전파된다..
  //전파하기 전에 이 함수를 거져서.. 전파해야 하니? 를 판단..
  //매개변수가 이전 데이터..
  //true -> 전파된다..
  //false -> 전파되지 않는다..
  @override
  bool updateShouldNotify(covariant InheritedWidget oldWidget) {
    return true;
  }

  //자손 누군가가 자신을 이용하기 위한 함수.. 클래스내에서는 static 으로..
  static MyInheritedWidget? of(BuildContext context) =>
      context.dependOnInheritedWidgetOfExactType<MyInheritedWidget>();
}

class TestSubWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    //조상중.. MyInheritedWidget 이라는 객체를 이용해.. 그 상태값을 사용..
    int count = MyInheritedWidget.of(context)!.count;
    return Container(
      width: 200,
      height: 200,
      color: Colors.yellow,
      child: Center(
        child: Text("sub widget: $count"),
      ),
    );
  }
}

class TestWidget extends StatelessWidget {
  TestWidget(){
    print("TestWidget constructor...");
  }
  @override
  Widget build(BuildContext context) {
    //위젯을 만들때.. 상태값이 있고. 그 상태값을 변경해서 re-rendering 되게 해야한다..
    //기본은 위젯 자체를 StatefulWidget-State 로 만들어야..
    //위젯의 일부분이 상태와 관련있다.. 그 일부분을 위해 전체 위젯을 StatefulWidget 으로 만들기
    //부담스럽다..
    //StatefulBuilder 라는 위젯으로.. 상태에 의해 변경되는 부분을 대체..
    return StatefulBuilder(
      //매개변수로.. 상태를 변경 화면을 update 할 수 있는 setState() 함수 전달..
      builder: (BuildContext context, StateSetter setState){
        MyInheritedWidget? widget = MyInheritedWidget.of(context);
        int counter = MyInheritedWidget.of(context)!.count;
        Function increment = MyInheritedWidget.of(context)!.increment;
        return Center(
          child: Container(
            color: Colors.red,
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              crossAxisAlignment: CrossAxisAlignment.stretch,
              children: [
                Text("counter : $counter"),
                ElevatedButton(
                  child: Text("increment"),
                  onPressed: () => setState(() => increment()),
                ),
                ElevatedButton(
                  child: Text("count++"),
                  onPressed: (){
                    setState(() => widget!.count++);
                  },
                ),
                TestSubWidget(),
              ],
            ),
          ),
        );
      },
    );
  }
}

