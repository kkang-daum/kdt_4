import 'package:flutter/material.dart';
import 'package:tripapp/models/trip_destination.dart';

class TripProvider with ChangeNotifier {
  List<TripDestination> _destinations = [
    TripDestination(
      id: 1,
      name: '스위스',
      country: 'Switzerland',
      description: '아름다운 알프스 산맥과 호수로 유명한 스위스',
      imagePath: 'assets/images/main_swiss.jpg',
      discount: '최대 20% 할인',
    ),
    TripDestination(
      id: 2,
      name: '호주',
      country: 'Australia',
      description: '광활한 자연과 독특한 문화를 체험할 수 있는 호주',
      imagePath: 'assets/images/main_australia.jpg',
      discount: '최대 10% 할인',
    ),
    TripDestination(
      id: 3,
      name: '조지아',
      country: 'Georgia',
      description: '코카서스의 숨겨진 왕국 조지아',
      imagePath: 'assets/images/main_georgia.jpg',
      discount: '최대 10% 할인',
    ),
    TripDestination(
      id: 4,
      name: '몽골',
      country: 'Mongolia',
      description: '끝없는 초원과 유목민의 나라',
      imagePath: 'assets/images/main_mongolia.jpg',
      discount: '최대 10% 할인',
    ),
    TripDestination(
      id: 3,
      name: '네팔',
      country: 'Nepal',
      description: '소박하지만 화려한 빛을 내는 나라',
      imagePath: 'assets/images/main_nepal.jpg',
      discount: '최대 10% 할인',
    ),
    TripDestination(
      id: 4,
      name: '하와이',
      country: 'Hawaiian',
      description: '세계 허니무너들의 지상낙원',
      imagePath: 'assets/images/main_hawaii.jpg',
      discount: '최대 10% 할인',
    ),
  ];

  List<TripDestination> get destinations => _destinations;

  TripDestination? getDestinationById(int id){
    return _destinations.firstWhere((destination) => destination.id == id);
  }
}