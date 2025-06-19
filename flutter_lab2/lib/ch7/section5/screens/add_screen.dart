import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_lab2/ch7/section5/bloc/todo_bloc.dart';
import 'package:flutter_lab2/ch7/section5/event/todo_event.dart';
import '../state/todo_state.dart';




class AddTodoScreen extends StatefulWidget {
  @override
  _AddTodoScreenState createState() => _AddTodoScreenState();
}

class _AddTodoScreenState extends State<AddTodoScreen> {
  final controller = TextEditingController();
  bool completedStatus = false;

  @override
  void dispose() {
    controller.dispose();
    super.dispose();
  }

  void onAdd() {

    final String title = controller.text;
    final bool completed = completedStatus;
    if (title.isNotEmpty) {
      final Todo todo = Todo(
        title: title,
        completed: completed,
      );
      //add........................................
      TodoBloc bloc = BlocProvider.of<TodoBloc>(context);
      bloc.add(AddTodoEvent(todo));
      Navigator.pop(context);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Add Todo'),
      ),
      body: ListView(
        children: <Widget>[
          Padding(
            padding: EdgeInsets.all(15.0),
            child: Container(
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.stretch,
                children: <Widget>[
                  TextField(controller: controller),
                  CheckboxListTile(
                    value: completedStatus,
                    onChanged: (checked) => setState(() {
                          completedStatus = checked ?? false;
                        }),
                    title: Text('Complete?'),
                  ),
                  ElevatedButton(
                    child: Text('Add'),
                    onPressed: onAdd,
                  ),
                ],
              ),
            ),
          )
        ],
      ),
    );
  }
}
