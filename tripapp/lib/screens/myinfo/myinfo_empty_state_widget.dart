import 'package:flutter/material.dart';

class MyInfoEmptyStateWidget extends StatelessWidget {
  Function(bool) showForm;

  MyInfoEmptyStateWidget(this.showForm); //상위 함수를 매개변수로 받아서..

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Icon(Icons.person_outline, size: 100, color: Colors.grey.shade400),
          SizedBox(height: 24),
          Text(
            "사용자 정보가 없습니다.",
            style: TextStyle(fontSize: 18, color: Colors.grey.shade600),
          ),
          SizedBox(height: 16),
          ElevatedButton(
              onPressed: () {
                //상위의 함수를 호출해서.. 화면에 출력되는 위젯을
                //정보 입력 위젯으로 바뀌게...
                showForm(true);
              }, 
              child: Text("정보 입력하기")
          ),
        ],
      ),
    );
  }
}
