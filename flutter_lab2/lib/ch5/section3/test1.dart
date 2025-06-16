import 'package:flutter/material.dart';
import 'package:intl/intl.dart';

main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: MainScreen(),
    );
  }
}

class MainScreen extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return MainScreenState();
  }
}
//SingleTickerProviderStateMixin - Ticker 제공.. 애니메이션 효과..
//TabController 에 ticker 지정해줘야..
class MainScreenState extends State<MainScreen> with SingleTickerProviderStateMixin{
  DateTime dateValue = DateTime.now();
  TimeOfDay timeValue = TimeOfDay.now();

  late TabController controller;//tab 화면 조정..

  @override
  void initState() {
    super.initState();
    //vsync : 탭 화면 조정시에 애니메이션 효과를 위한 Ticker 지정.. 현 클래스에 있다..
    controller = TabController(length: 3, vsync: this);
  }

  dialog(){

  }
  bottomSheet(){

  }
  //유저가 선택한 날짜를 리턴 시키는 함수..
  //dialog 가 닫겨야 발생한다.. 함수 호출자를 대기시키지 않기 위해서 Future(미래에 발생하는)
  Future datePicker() async {

  }
  Future timePicker() async {

  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('dialog test'),
        bottom: TabBar(//tab button, 꼭 AppBar 내에 들어가지 않아도 된다..
          controller: controller,
          tabs: [
            Tab(text: 'One',),
            Tab(text: 'Two',),
            Tab(text: 'Three',)
          ],
        ),
      ),
      body: TabBarView(
        controller: controller,//TabBar 와 동일 controller
        children: [
          Center(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                ElevatedButton(onPressed: dialog, child: Text('dialog'),),
                ElevatedButton(onPressed: bottomSheet, child: Text('bottomsheet'),),
                ElevatedButton(onPressed: datePicker, child: Text('datePicker'),),
                Text('date : ${DateFormat('yyyy-MM-dd').format(dateValue)}'),
                ElevatedButton(onPressed: timePicker, child: Text('timePicker'),),
                Text('time : ${timeValue.hour}:${timeValue.minute}'),
              ],
            ),
          ),
          Center(
            child: Text(
              'Two Screen',
              style: TextStyle(fontSize: 25, fontWeight: FontWeight.bold),
            ),
          ),
          Center(
            child: Text(
              'Three Screen',
              style: TextStyle(fontSize: 25, fontWeight: FontWeight.bold),
            ),
          )
        ],
      ),
    );
  }
}