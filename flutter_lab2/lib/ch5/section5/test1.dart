import 'package:flutter/material.dart';

main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return MyAppState();
  }
}

class MyAppState extends State<MyApp>{
  int selectedItem = 0;//선택된 navigation item 정보.. 상태..

  //tab 화면 효과..
  List<Widget> widgets = [
    Text('One Screen', style: TextStyle(fontSize: 25, fontWeight: FontWeight.bold),),
    Text('Two Screen', style: TextStyle(fontSize: 25, fontWeight: FontWeight.bold),),
    Text('Three Screen', style: TextStyle(fontSize: 25, fontWeight: FontWeight.bold),),
    Text('Four Screen', style: TextStyle(fontSize: 25, fontWeight: FontWeight.bold),),
  ];
  //bottom navigation bar item click 이벤트..
  onItemTapped(int index){
    setState(() {
      selectedItem = index;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: Scaffold(
        appBar: AppBar(
          bottom: PreferredSize(
            preferredSize: Size.fromHeight(48.0),
            child: Theme(
              data: ThemeData.from(
                colorScheme: ColorScheme.fromSwatch(accentColor: Colors.white)
              ),
              child: Container(
                height: 48.0,
                alignment: Alignment.center,
                child: Text('AppBar Bottom Text'),
              ),
            ),
          ),
          flexibleSpace: Container(
            decoration: BoxDecoration(
              image: DecorationImage(image: AssetImage('images/big.jpeg',), fit: BoxFit.fill)
            ),
          ),
          title: Text('AppBar Title'),
          actions: [
            IconButton(onPressed: (){}, icon: Icon(Icons.add_alert)),
            IconButton(onPressed: (){}, icon: Icon(Icons.phone))
          ],
        ),
        body: widgets.elementAt(selectedItem),
        bottomNavigationBar: BottomNavigationBar(
          type: BottomNavigationBarType.shifting,//fixed, shifting(
        ),
      ),
    );
  }
}