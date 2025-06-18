class Todo {
  String title;
  bool completed;
  Todo({required this.title, this.completed = false});

  void toggleCompleted(){
    completed = !completed;
  }
}