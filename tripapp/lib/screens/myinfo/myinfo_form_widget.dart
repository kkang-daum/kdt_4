import 'dart:io';

import 'package:flutter/material.dart';
import 'package:image_picker/image_picker.dart';

class MyInfoFormWidget extends StatefulWidget{
  @override
  State<StatefulWidget> createState() {
    return MyInfoFormWidgetState();
  }
}

class MyInfoFormWidgetState extends State<MyInfoFormWidget>{
  var nameController = TextEditingController();
  var emailController = TextEditingController();
  String? profileImagePath;
  ImagePicker picker = ImagePicker();

  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
      padding: EdgeInsets.all(16.0),
      child: Column(
        children: [
          //프로필 사진..
          Center(
            child: Stack(
              children: [
                CircleAvatar(
                  radius: 60,
                  backgroundImage: profileImagePath != null ?
                        FileImage(File(profileImagePath!))
                      : AssetImage("assets/images/default_profile.png") as ImageProvider
                ),
                Positioned(
                  bottom: 0,
                  right: 0,
                  child: FloatingActionButton(
                    mini: true,
                    onPressed: showImagePickerDialog,
                    child: Icon(Icons.camera_alt),
                  ),
                ),
              ],
            ),
          ),
          SizedBox(height: 32,),
          TextField(
            controller: nameController,
            decoration: InputDecoration(
              labelText: "이름",
              border: OutlineInputBorder(),
              prefixIcon: Icon(Icons.person),
            ),
          ),
          SizedBox(height: 16,),

          TextField(
            controller: emailController,
            decoration: InputDecoration(
              labelText: "이메일",
              border: OutlineInputBorder(),
              prefixIcon: Icon(Icons.email),
            ),
            keyboardType: TextInputType.emailAddress,
          ),
          SizedBox(height: 16,),

          ElevatedButton(
            onPressed: saveUserInfo,
            style: ElevatedButton.styleFrom(
              backgroundColor: Colors.blue,
              foregroundColor: Colors.white,
              padding: EdgeInsets.symmetric(vertical: 16),
            ),
            child: Text("저장"),
          ),
        ],
      ),
    );
  }

  void showImage
}