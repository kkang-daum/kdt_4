import 'dart:io';

import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:tripapp/providers/user_provider.dart';
import 'package:tripapp/routes/app_routes.dart';

class HomeDrawer extends StatelessWidget{
  @override
  Widget build(BuildContext context) {
    return Drawer(
      child: ListView(
        padding: EdgeInsets.zero,
        children: [
          Consumer<UserProvider>(
            builder: (context, userProvider, child){
              //drawer 상단을 사용자 정보로 많이 꾸미더라..
              //그 부분을 도와주기 위해서..
              return UserAccountsDrawerHeader(
                accountName: Text(userProvider.userInfo?.name ?? "홍길동"),
                accountEmail: Text(userProvider.userInfo?.email ?? "user@example.com"),
                currentAccountPicture: CircleAvatar(
                  backgroundImage: userProvider.userInfo?.profileImagePath != null
                    ? FileImage(File(userProvider.userInfo!.profileImagePath!),)
                      : AssetImage("assets/images/user_basic.jpg"),
                ),
                decoration: BoxDecoration(color: Colors.blue),
              );
            },
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