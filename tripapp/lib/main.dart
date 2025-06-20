import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:tripapp/providers/trip_provider.dart';
import 'package:tripapp/providers/user_provider.dart';
import 'package:tripapp/routes/app_routes.dart';
import 'package:tripapp/screens/home_screen.dart';
import 'package:tripapp/screens/myinfo_screen.dart';
import 'package:tripapp/services/database_helper.dart';

main() async{

  //앱이 실행되면서.. 위젯 로딩이 아니라.. 무언가 시간이 오래 걸리는 작업을
  //초반에 진행하고.. 그 작업 결과를 flutter 에서 이용해야 한다면..
  //framework 로딩이 되지도 않았는데.. 실행될 수 있는 상황이 발생한다..
  //flutter framework 로딩이 완료되었다는 것을 보장하라..
  WidgetsFlutterBinding.ensureInitialized();

  await DatabaseHelper.instance.database;

  //main 함수는 dart 의 entry point 이다..
  //이 함수내에서 꼭 flutter framework 을 사용해야 하는 것은 아니다..
  //print() 등으로 순수 dart 테스트도 했었다..
  //runApp() 은 flutter framework 을 로딩하고.. 개발자의 최초 widget 을 출력하라..
  //내부적으로.. flutter framework 이 로딩이 완료 될때까지 기다렸다가.. 매개변수의 위젯을 출력한다
  runApp(TripApp());
}

class TripApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MultiProvider(
        providers: [
          ChangeNotifierProvider(create: (_) => TripProvider()),
          ChangeNotifierProvider(create: (_) => UserProvider()..loadUserData()),
        ],
      child: MaterialApp(
        title: "Trip App",
        theme: ThemeData(
          primarySwatch: Colors.blue,
          useMaterial3: true,
        ),
        debugShowCheckedModeBanner: false,
        initialRoute: AppRoutes.home,
        routes: {
          AppRoutes.home: (context) => HomeScreen(),
          AppRoutes.myInfo: (context) => MyInfoScreen(),
        },
      ),
    );

  }
}