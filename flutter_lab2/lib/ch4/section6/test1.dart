import 'package:flutter/material.dart';

main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(title: Text("stack test"),),
        body: Stack(
          children: [
            Align(
              alignment: Alignment(0.0, 0.0),
              child: Container(
                width: 150,
                height: 150,
                color: Colors.yellow,
              ),
            ),
            Align(
              alignment: FractionalOffset(1.0, 0.0),
              child: Container(
                width: 150,
                height: 150,
                color: Colors.blue,
              ),
            ),
            Positioned(
              left: 40.0,
              top: 40.0,
              child: Container(
                width: 150,
                height: 150,
                color: Colors.pink,
              ),
            )
          ],
        ),
      ),
    );
  }
}