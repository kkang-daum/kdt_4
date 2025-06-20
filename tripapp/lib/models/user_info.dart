class UserInfo {
  String? name;
  String? email;
  String? profileImagePath;

  UserInfo({
    this.name, this.email, this.profileImagePath
  });

  //db insert 시에 이 객체를 지정.. 객체의 내용이 db 에 저장.. map 으로 만들어야해서..
  Map<String, dynamic> toMap(){
    return {
      "name": name,
      "email": email,
      "profileImagePath": profileImagePath
    };
  }
  //db select.. db 데이터로 객체가 생성되게..
  factory UserInfo
}