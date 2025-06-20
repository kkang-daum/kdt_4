import 'package:flutter/material.dart';

class HomeTopWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Stack(
      children: [
        Image.asset("assets/images/main_bg_1.jpg"),
        Container(
          padding: EdgeInsets.only(top: 70, left: 50,),
          child: Column(
            children: [
              Text(
                "연말연시 특별 할인 이벤트",
                style: TextStyle(
                  color: Colors.white,
                  fontSize: 20,
                  fontWeight: FontWeight.bold,
                ),
              ),
              SizedBox(height: 8,),
              Text(
                "최대 20% 할인",
                style: TextStyle(color: Colors.white, fontSize: 16,),
              )
            ],
          ),
        )
      ],
    );
  }
}