import 'package:flutter/material.dart';

void main() => runApp(MyApp());

//목록 구성 데이터..
class User {
  String name;
  String phone;
  String email;
  User(this.name, this.phone, this.email);
}

class MyApp extends StatelessWidget {
  List<User> users = [
    User('홍길동', '010001','a@a.com'),
    User('김길동', '010001','a@a.com'),
    User('이길동', '010001','a@a.com'),
    User('박길동', '010001','a@a.com'),
    User('최길동', '010001','a@a.com'),
    User('정길동', '010001','a@a.com'),
    User('강길동', '010001','a@a.com'),
  ];
  List<String> cities = ['서울시','부산시','대구시','인천시','광주시','대전시','울산시','세종시'];
  
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(title: Text('목록 테스트'),),
        body: Column(
          children: [
            Expanded(
                child: ListView.separated(
                  itemCount: users.length,//항목 갯수..
                  itemBuilder: (context, index){//항목 구성 위젯을 위해 자동 호출..
                    return ListTile(
                      leading: CircleAvatar(
                        radius: 25,
                        backgroundImage: AssetImage('images/big.jpeg'),
                      ),
                      title: Text(users[index].name),
                      subtitle: Text(users[index].phone),
                      trailing: Icon(Icons.more_vert),
                      onTap: (){
                        print(users[index].name);
                      },
                    );
                  },
                  separatorBuilder: (context, index){
                    return Divider(height: 2, color: Colors.black,);
                  },
                ),
            ),
            Expanded(
              child: GridView.builder(
                itemCount: cities.length,
                itemBuilder: (context, index){
                  return Card(
                    child: Column(
                      children: [
                        Text(cities[index]),
                        Image.asset('images/big.jpeg')
                      ],
                    ),
                  );
                },
                gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(crossAxisCount: 2),
              ),
            ),
          ],
        ),
      ),
    );
  }
}