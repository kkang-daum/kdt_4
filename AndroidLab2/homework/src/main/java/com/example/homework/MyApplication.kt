package com.example.homework

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

//앱 실행시 최초, 한번..
class MyApplication: Application() {
    //notification 을 띄우는 경우가 여러번이다..
    //notification 을 위한 channel 은 한번만 등록되어 있으면 되서...
    companion object {
        //알림을 발생시키는 곳에서 동일 id 를 이용해 발생시켜서.. 이전 알림이 제거되고.. 새로 뜨게.
        const val CHANNEL_ID = "my_notification_channel"
    }

    override fun onCreate() {
        super.onCreate()
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                CHANNEL_ID,
                "업데이트 알림",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "업데이트 알림을 위한 채널입니다."
            }

            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }
}