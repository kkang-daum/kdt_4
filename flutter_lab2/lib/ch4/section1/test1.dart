import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:google_fonts/google_fonts.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  //텍스트 애셋을 로딩하기 위한 개발자 함수..
  //파일 로딩이다.. 시간이 오래 걸린다..
  //Future : 미래에 발생하는 데이터.. 시간이 오래 걸려서 발생하는 데이터가 있다면..
  //이 데이터를 원하는 곳이 대기상태가 되는 문제..
  //Future 를 바로 리턴 시켜서.. 대기하지 않게 하고.. 실제 데이터가 발생하면 콜백으로
  //전달하겠다... 일종의 비동기..
  Future<String> loadText() async {
    return await rootBundle.loadString('assets/text/my_text.txt');
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData(
        //앱 전역에 적용되어야 하는 폰트 지정.. 물론 개발 화면에서 별도 폰트 지정도 가능하고
        fontFamily: 'myBasicFontName',
      ),
      home: Scaffold(
        appBar: AppBar(title: Text('Asset Test'),),
        body: Column(
          children: [
            Image.asset('images/icon.jpg'),
            Image.asset('images/icon/user.png'),
            //Future.. 미래에 발생하는 데이터.. 이 데이터가 발생했다는 것을 감지했다가..
            //실제 데이터가 발생하는 순간.. 그데이터로 화면을 구성하기 위한 위젯..
            FutureBuilder(
                future: loadText(),
                //두번째 매개변수가 발생한 데이터..
                builder: (context, snapshot){
                  return Text('assets text : ${snapshot.data}');
                }
            ),
            Text('안녕하세요.. 반갑습니다...'),
            Text(
                '안녕하세요.. 반갑습니다...',
                style: TextStyle(
                  fontFamily: 'myFontName',
                  fontSize: 16
                ),
            ),
            //google font 에서 제공하는 패키지를 이용하면.. 다운로드 등록 필요 없다..
            Text(
              '안녕하세요',
              style: GoogleFonts.singleDay(
                fontSize: 16,
                fontWeight: FontWeight.bold
              ),
            )
          ],
        ),
      ),
    );
  }
}