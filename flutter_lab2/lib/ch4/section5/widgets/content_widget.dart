import 'package:flutter/cupertino.dart';

class ContentWidget extends StatelessWidget {
  String txt = "동해물과 백두산이 마르고 닳도록 하나님이 보우아사 우리나라 만세...";
  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Text("좋아요 100개"),
        Text(
          txt,
          style: TextStyle(
            fontSize: 30
          ),
        )
      ],
    );
  }
}