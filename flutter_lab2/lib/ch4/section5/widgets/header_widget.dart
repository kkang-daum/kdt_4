import 'package:flutter/material.dart';
import 'package:fluttertoast/fluttertoast.dart';

const List<String> choices = [
  "신고", "공유하기", "링크 복사"
];

class HeaderWidget extends StatelessWidget {
  void select(String choice){
    Fluttertoast.showToast(
        msg: choice,
        toastLength: Toast.LENGTH_SHORT,
        //아래의 속성은 플랫폼에 따라 적용이 안될 수도 있다..
        gravity: ToastGravity.BOTTOM,
        backgroundColor: Colors.blue,
        textColor: Colors.white,
        fontSize: 16.0
    );
  }
  @override
  Widget build(BuildContext context) {
    return Row(
      children: [
        Image.asset('images/lab_instagram_icon_0.jpg'),
        Text('instagram'),
        Spacer(),
        PopupMenuButton<String>(
            //... 메뉴 클릭시의 이벤트 처리..
            onSelected: select,
            //... 클릭시 확장되는 부분을 구성하기 위해서 호출..
            itemBuilder: (BuildContext context){
              //이 함수에서 리턴시킨 부분이 확장되서 나온다..
              return choices.map<PopupMenuItem<String>>((String choice){
                return PopupMenuItem<String>(
                  value: choice,//메뉴 클릭시에.. onSelected 이벤트 콜백에 전달할 데이터
                  child: Text(choice),//각 메뉴 구성
                );
              }).toList();
            }
        ),
      ],
    );
  }
}