import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_lab2/ch7/section5/bloc/todo_bloc.dart';
import 'package:flutter_lab2/ch7/section5/event/todo_event.dart';
import '../state/todo_state.dart';

class TodoListItem extends StatelessWidget {
  final Todo todo;

  TodoListItem({required this.todo});

  @override
  Widget build(BuildContext context) {
    //add.....................
    TodoBloc bloc = BlocProvider.of<TodoBloc>(context);
    return ListTile(
      leading: Checkbox(
        value: todo.completed,
        onChanged: (bool? checked) {
          //bloc 에 이벤트 발생해서 앱 전역에 데이터 유지되게..
          bloc.add(ToggleCompletedTodoEvent(todo));
        },
      ),
      title: Text(todo.title),
      trailing: IconButton(
        onPressed: () {
          bloc.add(DeleteTodoEvent(todo));
        },
        icon: Icon(Icons.delete, color: Colors.red),
      ),
    );
  }
}
