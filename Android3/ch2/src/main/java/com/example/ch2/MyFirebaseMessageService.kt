package com.example.ch2

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

//fcm 관련.. 시스템에서 실행시키는 서비스...
class MyFirebaseMessageService : FirebaseMessagingService() {

    //앱 인스톨 되자마자.. fcm 서버가 전달하는 토큰을 전달하기 위해 자동 호출..
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        //적절하게 back-end 에 토큰 전달...
        Log.d("kkang", "fcm token:$token")
    }

    //fcm 메시지가 전달될 때마다..
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        //유저 알림...
        Log.d("kkang", "fcm message${message.data}")
    }
}