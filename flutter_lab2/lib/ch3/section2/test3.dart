import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

void main() => runApp(ParentWidget());

//위젯 트리의 최 상위 취젯을 StatefulWidget 으로 하는 것은 좋지 않다...
//단지.. 상위 위젯 상태 변경시에.. 하위 위젯의 lifecycle 이 어떻게 되는지를 테스트할 목적으로..
class ParentWidget extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return ParentWidgetState();
  }
}
class ParentWidgetState extends State<ParentWidget> {
  int count = 0;
  void increment(){
    setState(() {
      count++;
    });
  }
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text("Lifecycle Test"),
        ),
        //상위 위젯의 데이터를 하위에 전달하기 위한 코드..
        body: Provider.value(
            value: count,
            child: Center(
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Text("I am parent widget : $count"),
                  ChildWidget(),
                ],
              ),
            ),
        ),
        floatingActionButton: FloatingActionButton(
          onPressed: increment,
          child: Icon(Icons.add),
        ),
      ),
    );
  }
}

class ChildWidget extends StatefulWidget {
  ChildWidget(){
    print("ChildWidget constructor...");
  }
  @override
  State<StatefulWidget> createState() {
    return ChildWidgetState();
  }
}

//WidgetsBindingObserver : 앱 전체의 라이프사이클.. 감지하려고..
class ChildWidgetState extends State<ChildWidget> with WidgetsBindingObserver{
  int count = 0;
  ChildWidgetState(){
    print("ChildWidgetState constructor....");
  }
  @override
  void initState() {
    super.initState();
    print("ChildWidgetState initState...");
    //앱 상태 변경(화면 출력 상태)의 이벤트 감지 등록..
    WidgetsBinding.instance.addObserver(this);
  }
  @override
  void dispose() {
    super.dispose();
    WidgetsBinding.instance.removeObserver(this);
  }
  //initState 호출 후 자동 호출..
  //부모의 상태 값이 변경되거나.. 부모에서 위젯 트리 구조를 변경하면 호출..
  //부모의 상태 값을 받는 용도로 이용 가능..
  @override
  void didChangeDependencies() {
    super.didChangeDependencies();
    count = Provider.of<int>(context);//부모의 데이터 획득..
    print("ChildWidgetState... didChangeDependencies");
  }
  @override
  Widget build(BuildContext context) {
    print("ChildWidgetState... build");
    return Text("I am child : $count");
  }

  //앱 전체의 라이프사이클 변경시에 호출..
  @override
  void didChangeAppLifecycleState(AppLifecycleState state) {
    super.didChangeAppLifecycleState(state);
    switch(state){
      case AppLifecycleState.resumed:
      case AppLifecycleState.inactive:
        print("applifecycle resume..");
        break;
      case AppLifecycleState.hidden:
      case AppLifecycleState.paused:
      case AppLifecycleState.detached:
        print("applifecycle paused");
        break;
    }
  }
}