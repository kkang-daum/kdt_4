package com.example.ch5.section3

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.os.Message
import android.os.Messenger
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch5.R
import com.example.ch5.databinding.ActivityTest31Binding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Test3_1Activity : AppCompatActivity() {

    lateinit var binding: ActivityTest31Binding
    var connectionMode = "none"

    lateinit var messenger: Messenger//이곳에서 만든 메신저.. 외부 데이터 받기 위해서..
    lateinit var replyMessenger: Messenger//외부에서 전달한 메신저.. 외부에 데이터 전달하기 위해서

    var messengerJob: Job? = null//코루틴... duration 이 지정되면 초단위로 progressBar
    //데이터 업데이트..

    //상황에 따라.. 화면의 enable 상태 조정..
    fun changeViewEnable() = when(connectionMode){
        "messenger" -> {
            binding.playBtn.isEnabled = false
            binding.stopBtn.isEnabled = true
        }
        else -> {
            binding.playBtn.isEnabled = true
            binding.stopBtn.isEnabled = false

            binding.progressView.progress = 0
        }
    }

    //외부에서 전달한 데이터를 받기 위한 handler
    inner class HandleReplyMsg: Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when(msg.what){
                10 -> {
                    val bundle = msg.obj as Bundle
                    bundle.getInt("duration")?.let {
                        when {
                            it > 0 -> {
                                binding.progressView.max = it
                                //코루틴 구동 시켜서.. 코루틴에 의해 progressbar 증가..
                                val scope = CoroutineScope(Dispatchers.Default + Job())
                                messengerJob = scope.launch {
                                    while (binding.progressView.progress < binding.progressView.max){
                                        delay(1000)
                                        binding.progressView.incrementProgressBy(1000)
                                    }
                                }
                                changeViewEnable()
                            }
                            else -> {
                                connectionMode = "none"
                                unbindService(connection)
                                changeViewEnable()
                            }
                        }
                    }
                }
            }
        }
    }

    val connection: ServiceConnection = object: ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            //service 와 연결된 순간.. 서비스에서 전달한 객체 획득..
            messenger = Messenger(service)
            val msg = Message()
            msg.what = 10
            msg.replyTo = replyMessenger
            messenger.send(msg)
            connectionMode = "messenger"
        }

        override fun onServiceDisconnected(name: ComponentName?) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityTest31Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        replyMessenger = Messenger(HandleReplyMsg())
        binding.playBtn.setOnClickListener{
            //intent 로 서비스 실행..
            val intent = Intent("com.example.ch5_outer.ACTION_SERVICE_Messenger")
            //bindService 로 외부앱 실행시키려면.. 꼭 package 명시해야..
            //코드에서 외부 앱 패키지 이용하면 꼭 manifest 에 패키지 공개상태 명시해야..
            intent.setPackage("com.example.ch5_outer")
            bindService(intent, connection, BIND_AUTO_CREATE)
        }
        binding.stopBtn.setOnClickListener {
            val msg = Message()
            msg.what = 20
            messenger.send(msg)
            unbindService(connection)
            //코루틴 동작중이면.. 종료시켜야..
            messengerJob?.cancel()
            connectionMode = "none"
            changeViewEnable()
        }
    }

    override fun onStop() {
        super.onStop()
        if(connectionMode == "messenger"){
            val msg = Message()
            msg.what = 20
            messenger.send(msg)
            unbindService(connection)
        }
        connectionMode = "none"
        changeViewEnable()
    }
}