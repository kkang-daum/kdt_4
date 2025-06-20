import 'package:flutter/material.dart';
import 'package:tripapp/routes/app_routes.dart';

class HomeDrawer extends StatelessWidget{
  @override
  Widget build(BuildContext context) {
    return Drawer(
      child: ListView(
        padding: EdgeInsets.zero,
        children: [
          //drawer 상단을 사용자 정보로 많이 꾸미더라..
          //그 부분을 도와주기 위해서..
          UserAccountsDrawerHeader(
              accountName: Text("홍길동"),
              accountEmail: Text("user@example.com"),
              currentAccountPicture: CircleAvatar(
                backgroundImage: AssetImage("assets/images/user_basic.jpg"),
              ),
              decoration: BoxDecoration(color: Colors.blue),
          ),
          ListTile(
            leading: Icon(Icons.info),
            title: Text("About"),
            onTap: (){},
          ),
          ListTile(
            leading: Icon(Icons.person),
            title: Text("MyInfo"),
            onTap: (){
              Navigator.pop(context);
              Navigator.pushNamed(context, AppRoutes.myInfo);
            },
          )
        ],
      ),
    );
  }
}