package com.example.ch5.section3

import android.os.Bundle
import android.os.Messenger
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch5.R
import com.example.ch5.databinding.ActivityTest31Binding
import kotlinx.coroutines.Job

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_test31)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}