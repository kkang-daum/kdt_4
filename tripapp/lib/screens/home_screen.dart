import 'package:flutter/material.dart';
import 'package:tripapp/screens/home/home_middle_widget.dart';
import 'package:tripapp/screens/home/home_top_widget.dart';

class HomeScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Trip App"),
        actions: [
          IconButton(onPressed: (){}, icon: Icon(Icons.search),),
          IconButton(onPressed: (){}, icon: Icon(Icons.more_vert,),),
        ],
        backgroundColor: Colors.white,
        foregroundColor: Colors.black,
        elevation: 1,
      ),
      body: Column(
        children: [
          HomeTopWidget(),
          Expanded(
            child: Padding(
              padding: EdgeInsets.all(16.0),
              child: Column(
                children: [
                  HomeMiddleWidget(),
                ],
              ),
            ),
          )
        ],
      ),
    );
  }
}