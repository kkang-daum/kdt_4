import 'package:flutter/material.dart';
import 'package:flutter_lab2/ch7/section3/model/todo.dart';
import 'package:flutter_lab2/ch7/section3/widgets/todo_list_item.dart';

class TodoList extends StatelessWidget {
  List<Todo> todos;
  TodoList(this.todos);
  @override
  Widget build(BuildContext context) {
    return ListView(
      children: getChildrenTodos(),
    );
  }
  List<Widget> getChildrenTodos(){
    return todos.map((todo) => TodoListItem(todo)).toList();
  }
}