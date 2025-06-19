import '../state/todo_state.dart';

abstract class TodoEvent{}

class AddTodoEvent extends TodoEvent {
  Todo todo;
  AddTodoEvent(this.todo);
}

class DeleteTodoEvent extends TodoEvent {
  Todo todo;
  DeleteTodoEvent(this.todo);
}

class ToggleCompletedTodoEvent extends TodoEvent {
  Todo todo;
  ToggleCompletedTodoEvent(this.todo);
}