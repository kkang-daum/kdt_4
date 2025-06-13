import 'package:flutter/cupertino.dart';

class ImageWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Image.asset(
      'images/lab_instagram.jpg',
      width: double.infinity,//가로 사이즈 전체 차지하라...
    );
  }
}