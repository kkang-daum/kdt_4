import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_lab2/ch7/section5/event/todo_event.dart';
import 'package:flutter_lab2/ch7/section5/state/todo_state.dart';

class TodoBloc extends Bloc<TodoEvent, TodoState>{
  TodoBloc(): super(TodoState([])){
    on<AddTodoEvent>((event, emit){
      //상태는 불변이다.. 과거의 상태를 참조해서.. 새로운 상태를 만드는 것이지..
      //과거의 상태를 변경시키는 것이 아니다..
      List<Todo> newTodos = List.from(state.todos)
          ..add(event.todo);
      emit(TodoState(newTodos));
    });

    on<DeleteTodoEvent>((event, emit){
      List<Todo> newTodos = List.from(state.todos)
          ..remove(event.todo);
      emit(TodoState(newTodos));
    });

    on<ToggleCompletedTodoEvent>((event, emit){
      List<Todo> newTodo = List.from(state.todos);
      int index = newTodo.indexOf(event.todo);
      newTodo[index].toggleCompleted();
      emit(TodoState(newTodo));
    });
  }

  @override
  void onTransition(Transition<TodoEvent, TodoState> transition) {
    super.onTransition(transition);
    print(transition);
  }
}