package com.example.homework

import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import kotlin.concurrent.thread

class UpdateService : Service() {

    private fun updateProgressNotification(currentProgress: Int, totalSteps: Int){
        //noti 로 progress 값 지정..
        val builder = NotificationCompat.Builder(this, MyApplication.CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_popup_sync)
            .setContentTitle("앱 업데이트")
            .setProgress(totalSteps, currentProgress, false)
            .setOngoing(true)//유저에 의해 취소 못되게..

        startForeground(11, builder.build())
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        updateProgressNotification(0, 10)
        thread {
            for(i in 0..10){
                updateProgressNotification(i, 10)
                Thread.sleep(1000)
            }
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                stopForeground(STOP_FOREGROUND_REMOVE)
            }else {
                stopForeground(true)
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}