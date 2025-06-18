import 'package:flutter/material.dart';
import 'package:flutter_lab2/ch7/section3/providers/todo_provider.dart';
import 'package:flutter_lab2/ch7/section3/screen/add_screen.dart';
import 'package:flutter_lab2/ch7/section3/widgets/todo_list.dart';
import 'package:provider/provider.dart';

class HomeScreen extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return HomeScreenState();
  }
}

class HomeScreenState extends State<HomeScreen>
    with SingleTickerProviderStateMixin {
  late TabController controller;

  @override
  void initState() {
    super.initState();
    controller = TabController(length: 3, vsync: this);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Todos"),
        actions: [
          IconButton(
            onPressed: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => AddTodoScreen()),
              );
            },
            icon: Icon(Icons.add),
          ),
        ],
        bottom: TabBar(
          controller: controller,
          tabs: [
            Tab(text: "All",),
            Tab(text: "Active",),
            Tab(text: "Complete",),
          ],
        ),
      ),
      body: Consumer<TodosModel>(
        builder: (context, model, child){
          return TabBarView(
            controller: controller,
            children: [
              TodoList(model.todos),
              TodoList(model.todos.where((todo) => !todo.completed).toList()),
              TodoList(model.todos.where((todo) => todo.completed).toList(),)
            ],
          );
        },
      ),
    );
  }
}
