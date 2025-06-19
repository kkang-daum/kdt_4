import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_lab2/ch7/section5/bloc/todo_bloc.dart';
import 'screens/home_screen.dart';


void main() => runApp(TodosApp());

class TodosApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    //add.................................
    return MaterialApp(
      home: BlocProvider<TodoBloc>(
        create: (context) => TodoBloc(),
        child: HomeScreen(),
      ),
    );
  }
}