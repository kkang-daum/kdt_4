import 'package:flutter/material.dart';

main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  String longTxt = "동해물과 배두산이 마르고 닳도록 하나님이 보우아사 우리나라 만세. 동해물과 배두산이 마르고 닳도록 하나님이 보우아사 우리나라 만세. ";
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(title: Text("Image Text Test"),),
        body: Column(
          children: [
            Text(
              "Hello World",
              style: TextStyle(
                fontWeight: FontWeight.bold,
                fontStyle: FontStyle.italic,
                color: Colors.red,
                fontSize: 20,
                backgroundColor: Colors.yellow,
                decoration: TextDecoration.underline,
                decorationColor: Colors.red,
                decorationStyle: TextDecorationStyle.wavy
              ),
            ),
            Text(
              longTxt,
              style: TextStyle(
                fontSize: 20,
              ),
              maxLines: 2,
              overflow: TextOverflow.fade,
            ),
            Image(
                //ImageProvider - 이미지 데이터 획득 제공자 역할..
                //provider 가 이미 지정된 생성자를 이용할 수도 있고..
                image: NetworkImage(
                  "https://upload.wikimedia.org/wikipedia/commons/thumb/4/41/Sunflower_from_Silesia2.jpg/1280px-Sunflower_from_Silesia2.jpg"
                ),
                width: 200,
                height: 200,
            ),
            //독자적인 내용을 출력하기 위한 위젯이 아니라..
            //화면에 출력되는 특정 영역의 속성을 설정하기 위한 위젯..
            //여백, 보더, 사이즈...
            Container(
              width: 200,
              height: 200,
              child: Stack(
                children: [
                  Container(width: 200, height: 200, color: Colors.red,),
                  Image.asset(
                      "images/big.jpeg",
                      width: 200,
                      height: 200,
                      fit: BoxFit.none,)
                ],
              ),
            ),
            CircleAvatar(
              radius: 50,
              backgroundImage: AssetImage('images/big.jpeg'),
            ),
            Container(
              width: 100,
              height: 100,
              decoration: BoxDecoration(
                shape: BoxShape.circle,
                image: DecorationImage(
                  image: AssetImage('images/big.jpeg'),
                  fit: BoxFit.cover
                )

              ),
            )
          ],
        ),
      ),
    );
  }
}