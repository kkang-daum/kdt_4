package com.example.ch5.section1

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch5.R
import com.example.ch5.databinding.ActivityTest11Binding

class Test1_1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityTest11Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val intent = Intent(this, MyService::class.java)

        binding.startBtn.setOnClickListener {
            startService(intent)
        }
        binding.stopBtn.setOnClickListener {
            stopService(intent)
        }

        //bindService......................
        val connection = object: ServiceConnection {
            //서비스에서 객체 전달되는 순간 호출.. 두번째 매개변수가 서비스의 객체..
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                val serviceBinder = service as MyService.MyBinder
                val result = serviceBinder.funA(10)
                Log.d("kkang", "activity.. result : $result")
            }

            override fun onServiceDisconnected(name: ComponentName?) {

            }
        }

        binding.bindBtn.setOnClickListener {
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
        binding.unbindBtn.setOnClickListener {
            unbindService(connection)
        }
    }
}