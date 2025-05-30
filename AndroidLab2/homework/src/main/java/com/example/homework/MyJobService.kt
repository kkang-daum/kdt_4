package com.example.homework

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat

class MyJobService : JobService() {

    override fun onStartJob(params: JobParameters?): Boolean {
        //action event 준비..
        val updateIntent = Intent(this, UpdateActionReceiver::class.java)
        val pIntent = PendingIntent.getBroadcast(this, 0, updateIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        //notification
        val builder = NotificationCompat.Builder(this, MyApplication.CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_popup_sync)
            .setContentTitle("업데이트 가능")
            .setContentText("WIFI에 연결되었습니다. 지금 업데이트 하시겠습니까?")
            .setAutoCancel(true)
            .addAction(android.R.drawable.ic_menu_upload, "업데이트", pIntent)

        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(11, builder.build())

        return false
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        return true
    }
}