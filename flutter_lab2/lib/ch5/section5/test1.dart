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
          type: BottomNavigationBarType.shifting,//fixed(고정), shifting(애니메이션)
          items: <BottomNavigationBarItem>[
            BottomNavigationBarItem(
              icon: Icon(Icons.home),
              label: 'One',
              backgroundColor: Colors.green,//이 item 이 선택될 때, navigation bar 전체 색상
            ),
            BottomNavigationBarItem(
              icon: Icon(Icons.business),
              label: 'Two',
              backgroundColor: Colors.red,//이 item 이 선택될 때, navigation bar 전체 색상
            ),
            BottomNavigationBarItem(
              icon: Icon(Icons.school),
              label: 'Three',
              backgroundColor: Colors.purple,//이 item 이 선택될 때, navigation bar 전체 색상
            ),
            BottomNavigationBarItem(
              icon: Icon(Icons.school),
              label: 'Four',
              backgroundColor: Colors.pink,//이 item 이 선택될 때, navigation bar 전체 색상
            ),
          ],
          currentIndex: selectedItem,
          selectedItemColor: Colors.amber,//선택된 아이템을 표시할 색상.. 아이콘의 색상..
          onTap: onItemTapped,
        ),
        drawer: Drawer(
          child: ListView(
            padding: EdgeInsets.zero,
            children: [
              DrawerHeader(
                child: Text('Drawer header'),
                decoration: BoxDecoration(
                  color: Colors.blue
                ),
              ),
              ListTile(
                title: Text('Item1'),
                onTap: (){},
              ),
              ListTile(
                title: Text('Item2'),
                onTap: (){},
              )
            ],
          ),
        ),
      ),
    );
  }
}