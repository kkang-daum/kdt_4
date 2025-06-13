import 'package:flutter/material.dart';

//card data 추상화...
class DataVO {
  String image;
  String title;
  String date;
  String content;
  String location;

  DataVO(this.image, this.title, this.date, this.content, this.location);
}

//서버 데이터라는 가정..
List<DataVO> datas = [
  DataVO(
    'images/lab_lotte_1.jpg',
    '롯데웨딩워크',
    '각 지점 본 매장',
    '6.13(금)~6.15(일)',
    '백화점 전점'
  ),
  DataVO(
      'images/lab_lotte_2.jpg',
      '롯데웨딩워크',
      '각 지점 본 매장',
      '6.13(금)~6.15(일)',
      '백화점 전점'
  ),
  DataVO(
      'images/lab_lotte_3.jpg',
      '롯데웨딩워크',
      '각 지점 본 매장',
      '6.13(금)~6.15(일)',
      '백화점 전점'
  ),
  DataVO(
      'images/lab_lotte_4.jpg',
      '롯데웨딩워크',
      '각 지점 본 매장',
      '6.13(금)~6.15(일)',
      '명동점'
  ),
  DataVO(
      'images/lab_lotte_5.jpg',
      '롯데웨딩워크',
      '각 지점 본 매장',
      '6.13(금)~6.15(일)',
      '잠실점'
  ),
];

class CardWidget extends StatelessWidget {
  DataVO vo;

  CardWidget(this.vo);

  @override
  Widget build(BuildContext context) {
    return Stack(
      children: [
        Container(
          color: Colors.pink,
        ),
        Align(
          alignment: Alignment(0.0, 0.0),
          child: Stack(
            children: [
              Column(
                mainAxisSize: MainAxisSize.min,
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Image.asset(
                    vo.image,
                    width: 350,
                  ),
                  Container(
                    width: 350,
                    height: 100,
                    color: Colors.white,
                    padding: EdgeInsets.only(left: 16, top: 8, bottom: 8),
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(
                          vo.title,
                          style: TextStyle(
                            fontSize: 15,
                            fontWeight: FontWeight.bold
                          ),
                        ),
                        Spacer(),
                        Text(vo.content),
                        Text(vo.date),
                      ],
                    ),
                  )
                ],
              )
            ],
          ),
        )
      ],
    );
  }
}