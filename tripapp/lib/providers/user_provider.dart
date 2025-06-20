import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:tripapp/models/user_info.dart';
import 'package:tripapp/services/database_helper.dart';

class UserProvider with ChangeNotifier {
  UserInfo? _userInfo;
  UserInfo? get userInfo => _userInfo;

  //저장된 user 정보가 있는지 판단하기 위해서 호출...
  bool get hasUserInfo => _userInfo != null &&
      (_userInfo!.name?.isNotEmpty == true || _userInfo!.email?.isNotEmpty == true);

  Future<void> loadUserData() async {
    _userInfo = await DatabaseHelper.instance.getUser();
    notifyListeners();
  }
  Future<void> updateUserInfo(UserInfo userInfo) async {
    await DatabaseHelper.instance.insertOrUpdateUser(userInfo);
    _userInfo = userInfo;
    notifyListeners();
  }
  Future<void> clearUserInfo() async {
    _userInfo = null;
    notifyListeners();
  }
}