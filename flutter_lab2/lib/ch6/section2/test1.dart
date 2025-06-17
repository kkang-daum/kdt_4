import 'dart:io';

import 'package:dio/dio.dart';
import 'package:flutter/material.dart';

main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return MyAppState();
  }
}

class MyAppState extends State<MyApp>{
  
  List datas = [];//서버에서 받은 데이터.. 상태 데이터..

  ScrollController controller = ScrollController();
  
  int page = 1;
  int seed = 1;
  
  //서버 요청 함수..
  Future<List<dynamic>> dioTest() async {
    try {
      var dio = Dio(BaseOptions(
        connectTimeout: Duration(seconds: 10),
        receiveTimeout: Duration(seconds: 10),
        headers: {
          HttpHeaders.contentTypeHeader: 'application/json',
          HttpHeaders.acceptHeader: 'application/json'
        }
      ));
      
      Response<dynamic> response = await dio.get(
        "https://randomuser.me/api/?seed=${seed}&page=${page}&results=20"
      );
      return response.data['results'];
    }catch(e){
      print(e);
    }
    return [];
  }
  
  scrollListener() async {
    if(controller.offset >= controller.position.maxScrollExtent && 
        !controller.position.outOfRange){
      page++;
      List result = await dioTest();
      setState(() {
        datas.addAll(result);
      });
    }
  }
  
  @override
  void initState() {
    super.initState();
    controller.addListener(scrollListener);
    dioTest().then((value){
      setState(() {
        datas = value;
      });
    });
  }
  
  @override
  void dispose() {
    super.dispose();
    controller.dispose();
  }
  
  Future<void> refresh() async {
    page = 1;
    seed++;
    List result = await dioTest();
    setState(() {
      datas = result;
    });
  }
  
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(title: Text('dio test'),),
        body: RefreshIndicator(
          onRefresh: refresh,
          child: ListView.separated(itemBuilder: itemBuilder, separatorBuilder: separatorBuilder, itemCount: itemCount),
        ),
      ),
    );
  }
}