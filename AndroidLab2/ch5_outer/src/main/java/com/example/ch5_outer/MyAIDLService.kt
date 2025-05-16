package com.example.ch5_outer

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder

class MyAIDLService : Service() {

    lateinit var player: MediaPlayer

    override fun onCreate() {
        super.onCreate()
        player = MediaPlayer()
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }

    override fun onBind(intent: Intent): IBinder {
        //업무를 가지는 객체를 넘기는 것이 아니라.. 프로세스간 통신을 대행하는 Stub 객체를 넘기는 것이다..
        //Stub 도 aidl 을 구현함으로.. 함수명은 동일..
        return object : MyAIDLInterface.Stub(){
            override fun getDuration(): Int {
                return if(player.isPlaying)
                    player.duration
                else 0
            }

            override fun start() {
                if(!player.isPlaying){
                    player = MediaPlayer.create(this@MyAIDLService, R.raw.music)
                    try {
                        player.start()
                    }catch (e: Exception){
                        e.printStackTrace()
                    }
                }
            }

            override fun stop() {
                if(player.isPlaying)
                    player.stop()
            }
        }
    }
}