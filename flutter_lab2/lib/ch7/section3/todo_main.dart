import 'package:flutter/material.dart';
import 'package:flutter_lab2/ch3/section3/test2.dart';
import 'package:flutter_lab2/ch7/section3/providers/todo_provider.dart';
import 'package:provider/provider.dart';

main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider(
      create: (context) => TodosModel(),
      child: MaterialApp(
        home: HomeScreen(),
      ),
    );
  }
}