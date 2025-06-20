import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:tripapp/providers/user_provider.dart';
import 'package:tripapp/routes/app_routes.dart';
import 'package:tripapp/screens/myinfo/myinfo_empty_state_widget.dart';
import 'package:tripapp/screens/myinfo/myinfo_form_widget.dart';


//사용자 설정 정보가 있는지에 따라.. 화면에 출력되는 위젯을 다르게..
//상태가 유지되어야 하는데.. 앱 전역.. 여러 위젯간의 상태는 아니다..
class MyInfoScreen extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return MyInfoScreenState();
  }
}

class MyInfoScreenState extends State<MyInfoScreen>{
  bool _showForm = false;//화면에 출력되는 위젯을 조정하기 위한 상태..

  @override
  void initState() {
    super.initState();
    final userProvider = Provider.of<UserProvider>(context, listen: false);
    if(userProvider.userInfo != null){
      _showForm = true;
    }
  }

  void handleShowForm(bool shouldShow){
    setState(() {
      _showForm = shouldShow;
    });
  }
  
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("MyInfo"),
        backgroundColor: Colors.white,
        foregroundColor: Colors.black,
        elevation: 1,
        actions: [
          IconButton(
            icon: Icon(Icons.home),
            onPressed: (){
              Navigator.pushNamedAndRemoveUntil(
                //home 에서 뿐만이 아니라.. 다른 화면에서 사용자 정보를 위해 들어 올수도 있다는가정
                //화면 stack 을 모두 지우고 home 으로 이동..
                context, 
                AppRoutes.home,
                  (route) => false
              );
            },
          )
        ],
      ),
      body: Consumer<UserProvider>(
        builder: (context, userProvider, child){
          if(!userProvider.hasUserInfo && !_showForm){
            return MyInfoEmptyStateWidget(handleShowForm);
          }else {
            return MyInfoFormWidget();
          }
        },
      ),
    );
  }
}