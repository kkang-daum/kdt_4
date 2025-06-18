import 'package:flutter/material.dart';

main() => runApp(ParentWidget());

//자식에서 발생하는 상태를 부모에서 유지해서.. 형제들이 공유..
//단순 부모에게만 상태가 유지되면 되는 경우에 유용한 방식이다..
//계층이 복잡한 경우에는 비 합리적인 방식이다..

class ParentWidget extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return ParentWidgetState();
  }
}

class ParentWidgetState extends State<ParentWidget>{
  //부모가 유지하는 상태 데이터..
  //상태를 변경하는 것은 하위 위젯이..
  //상태를 이용하는 것도 하위 위젯이..
  bool favorited = false;
  int favoriteCount = 10;

  //상태를 변경하기 위해서 호출되는 함수..
  //상태가 발생하는 곳은 자식이다.. 자식이 호출할 함수이다..
  void toggleFavorite(){
    setState(() {
      if(favorited){
        favoriteCount -= 1;
        favorited = false;
      }else {
        favoriteCount += 1;
        favorited = true;
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(title: Text("state test"),),
        body: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            //자신의 상태를 자식이 이용.. 변경... 생성자 매개변수로 전달..
            IconWidget(favorited: favorited, onChanged: toggleFavorite),
            ContentWidget(favoriteCount: favoriteCount),
          ],
        ),
      ),
    );
  }
}