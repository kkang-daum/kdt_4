package com.example.ch5_outer

import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.os.Message
import android.os.Messenger

//원천 업무를 제공하는 앱의 서비스..
//외부 앱이 bindService() 로 이용하는 서비스..
class MyMessengerService : Service() {

    lateinit var messager: Messenger//이 앱에서 만든 메신저..  외부 앱이 이 메신저를 이용해 데이터 전달..
    lateinit var replyMessenger: Messenger//외부 앱이 만든 메신저.. 이 메신저로 외부앱에 데이터 전달..
    //음원 및 영상 play
    lateinit var player: MediaPlayer

    override fun onCreate() {
        super.onCreate()
        player = MediaPlayer()
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }

    //외부에서 데이터를 전달할 때 실행될 함수..
    //어디선가 메시지를 전달하면 메시지 큐에 샇이게 되고..
    //누군가가 그 메시지 큐를 감지하고 있다가.. 뭔가가 쌓이면.. 큐에서 메시지를 뽑아서.. 실행을 시킨다..looper
    //우리가 looper 를 만들수도 있지만. 기본으로 만들어진 looper 를 사용하겠다는 의미..
    inner class IncomingHandler(context: Context): Handler(Looper.getMainLooper()){
        //외부에서 send 하는 순간 실행.. 매개변수는 외부에서 전달한 데이터..
        override fun handleMessage(msg: Message) {
            when(msg.what){
                //5.........................
                10 -> {
                    //음원 play 요청이라고 가정..
                    //외부에서 전달한 데이터중에.. 결과 데이터를 받을 messenger 까지도 넘어왔다..
                    replyMessenger = msg.replyTo
                    if(!player.isPlaying){
                        //현재 음악이 play 되지 않고 있다면..
                        player = MediaPlayer.create(this@MyMessengerService, R.raw.music)
                        try{
                            val replyMsg = Message()
                            replyMsg.what = 10
                            val replyBundle = Bundle()
                            replyBundle.putInt("duration", player.duration)
                            replyMsg.obj = replyBundle
                            //6..............
                            replyMessenger.send(replyMsg)//외부에 데이터 전달 순간..

                            //음원 play...
                            player.start()
                        }catch (e: Exception){
                            e.printStackTrace()
                        }
                    }
                }
                //b.....................
                20 -> {
                    //stop 요청...
                    if(player.isPlaying)
                        player.stop()
                }
            }
        }
    }

    override fun onBind(intent: Intent): IBinder {
        //2.......................
        messager = Messenger(IncomingHandler(this))
        return messager.binder
    }
}