package com.example.ch4.section2

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.appcompat.app.AppCompatActivity.NOTIFICATION_SERVICE
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput

class MyRemoteinputReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        //원격지(noti)에서 입력한 문자열 획득..
        val txt = RemoteInput.getResultsFromIntent(intent)
            ?.getCharSequence("key_reply")
        Log.d("kkang", "receiver... $txt")

        //받은 다음에.. 발생한 notification 을 동일 id 로 갱신하거나 취소시키지 않으면
        //nofi는 빙빙빙 상태..

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val builder: NotificationCompat.Builder
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                "oneChannel",
                "My Channel",
                NotificationManager.IMPORTANCE_HIGH
            )
            //만들어진 채널을 시스템에 등록..
            manager.createNotificationChannel(channel)
            //등록된 채널을 이용해 Builder 생성..
            builder = NotificationCompat.Builder(context, "oneChannel")
        }else {
            builder = NotificationCompat.Builder(context)
        }

        builder.setSmallIcon(android.R.drawable.ic_notification_overlay)
        builder.setWhen(System.currentTimeMillis())
        builder.setContentTitle("답장 완료")
        builder.setContentText("메시지가 성공적으로 전송되었습니다.")

        manager.notify(11, builder.build())


    }
}