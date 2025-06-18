import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_lab2/ch7/section3/model/todo.dart';
import 'package:flutter_lab2/ch7/section3/providers/todo_provider.dart';
import 'package:provider/provider.dart';

//상태 데이터(provider)를 이용해서 화면 구성..
class TodoListItem extends StatelessWidget {
  //원한다면 이곳에서 provider 를 이용할 수 있지만..
  //상위에서 데이터를 획득하고.. 자신들은 상위에서 전달하는 데이터를 출력만..
  Todo todo;
  TodoListItem(this.todo);
  @override
  Widget build(BuildContext context) {
    return ListTile(
      leading: Checkbox(
        value: todo.completed,
        onChanged: (bool? checked){
          //checkbox 상태가 변경되었다.. 앱 전역에서 유지되는 provider 데이터가 변경되어야 한다.
          Provider.of<TodosModel>(context, listen: false).toggleTodo(todo);
        },
      ),
      title: Text(todo.title),
      trailing: IconButton(
          onPressed: (){
            Provider.of<TodosModel>(context, listen: false).deleteTodo(todo);
          },
          icon: Icon(
            Icons.delete,
            color: Colors.red,
          ),
      ),
    );
  }
}