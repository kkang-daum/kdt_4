import 'dart:math';

import 'package:flutter/material.dart';
import 'package:geolocator/geolocator.dart';

main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return MyAppState();
  }
}

class MyAppState extends State<MyApp>{
  String? latitude;
  String? longitude;
  
  getGeoData() async {
    LocationPermission permission = await Geolocator.checkPermission();
    if(permission == LocationPermission.denied){
      permission = await Geolocator.requestPermission();
      if(permission == LocationPermission.denied){
        setState(() {
          latitude = "no permission";
        });
      }else {
        Position position = await Geolocator.getCurrentPosition();
        setState(() {
          latitude = position.latitude.toString();
          longitude = position.longitude.toString();
        });
      }
    }else {
      Position position = await Geolocator.getCurrentPosition();
      setState(() {
        latitude = position.latitude.toString();
        longitude = position.longitude.toString();
      });
    }
  }
  
  @override
  void initState() {
    super.initState();
    getGeoData();
  }
  
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(title: Text("geolocator test"),),
        body: Column(
          children: [
            Text("$latitude"),
            Text("$longitude")
          ],
        ),
      ),
    );
  }
}