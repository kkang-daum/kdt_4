//bloc 을 통해 유지하고 발행하고자 하는 상태 데이터..
class Todo {
  String title;
  bool completed;
  Todo({required this.title, this.completed = false});
  void toggleCompleted(){
    completed = !completed;
  }
}

class TodoState {
  List<Todo> todos;
  TodoState(this.todos);
}