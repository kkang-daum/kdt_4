import 'package:flutter/material.dart';
import 'package:tripapp/routes/app_routes.dart';
import 'package:tripapp/screens/home_screen.dart';

main() {
  runApp(TripApp());
}

class TripApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "Trip App",
      theme: ThemeData(
        primarySwatch: Colors.blue,
        useMaterial3: true,
      ),
      debugShowCheckedModeBanner: false,
      initialRoute: AppRoutes.home,
      routes: {
        AppRoutes.home: (context) => HomeScreen(),
      },
    );
  }
}