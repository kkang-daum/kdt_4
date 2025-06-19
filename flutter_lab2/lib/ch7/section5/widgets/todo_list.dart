import 'package:flutter/material.dart';
import 'package:flutter_lab2/ch7/section5/widgets/todo_list_item.dart';
import '../state/todo_state.dart';


class TodoList extends StatelessWidget {
  final List<Todo> todos;

  TodoList({required this.todos});

  @override
  Widget build(BuildContext context) {
    return ListView(
      children: getChildrenTodos(),
    );
  }

  //add...................
  List<Widget> getChildrenTodos(){
    return todos.map((todo) => TodoListItem(todo: todo)).toList();
  }
}
