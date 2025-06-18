import 'package:flutter/material.dart';
import 'package:flutter_lab2/ch7/section3/model/todo.dart';
import 'package:flutter_lab2/ch7/section3/providers/todo_provider.dart';
import 'package:provider/provider.dart';

class AddTodoScreen extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return AddTodoScreenState();
  }
}

class AddTodoScreenState extends State<AddTodoScreen>{
  var controller = TextEditingController();
  bool completedStatus = false;

  void onAdd(){
    //유저 입력데이터 획득..
    String title = controller.text;
    bool completed = completedStatus;
    if(title.isNotEmpty){
      //입력 데이터를 앱 전역에서 provider 로 유지해야 한다..
      Todo todo = Todo(title: title, completed: completed);
      Provider.of<TodosModel>(context, listen: false).addTodo(todo);
      //자동으로 이전 화면으로 되돌아가라..
      Navigator.pop(context);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Add Todo"),),
      body: ListView(
        children: [
          Padding(
            padding: EdgeInsets.all(15.0),
            child: Container(
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.stretch,
                children: [
                  TextField(controller: controller,),
                  CheckboxListTile(
                      value: completedStatus,
                      onChanged: (checked) => setState(() {
                        completedStatus = checked ?? false;
                      }),
                    title: Text("completed?"),
                  ),
                  ElevatedButton(onPressed: onAdd, child: Text("ADD")),
                ],
              ),
            ),
          )
        ],
      ),
    );
  }
}