//bloc 에 발생시키는 이벤트..
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

abstract class CounterEvent {
  int no;

  CounterEvent(this.no);
}

class IncrementEvent extends CounterEvent {
  IncrementEvent(super.no);
}

class DecrementEvent extends CounterEvent {
  DecrementEvent(super.no);
}

//Bloc
class BlocCounter extends Bloc<CounterEvent, int> {
  BlocCounter() : super(0) {
    on<IncrementEvent>((event, emit) {
      //state 는 내장 변수.. 이 이벤트가 발생하기 전에 bloc 에서 유지하던 상태 데이터
      emit(state + event.no);
    });
    on<DecrementEvent>((event, emit) {
      emit(state - event.no);
    });
  }

  @override
  void onTransition(Transition<CounterEvent, int> transition) {
    super.onTransition(transition);
    print("transition.... $transition");
  }
}

main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(title: Text("bloc text")),
        body: BlocProvider<BlocCounter>(
          create: (context) => BlocCounter(),
          child: MyWidget(),
        ),
      ),
    );
  }
}

class MyWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    BlocCounter bloc = BlocProvider.of<BlocCounter>(context);
    return BlocBuilder<BlocCounter, int>(
      builder: (context, count) {
        return Column(
          children: [
            Text("${bloc.state}"),
            ElevatedButton(
              onPressed: () {
                bloc.add(IncrementEvent(2));
              },
              child: Text('increment'),
            ),
            ElevatedButton(
              onPressed: () {
                bloc.add(DecrementEvent(2));
              },
              child: Text('decrement'),
            ),
          ],
        );
      },
    );
  }
}
