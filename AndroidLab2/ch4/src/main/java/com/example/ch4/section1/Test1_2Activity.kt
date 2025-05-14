package com.example.ch4.section1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch4.R
import com.example.ch4.databinding.ActivityTest12Binding

class Test1_2Activity : AppCompatActivity() {

    lateinit var screenReceiver: BroadcastReceiver
    lateinit var batteryReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityTest12Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //screen on/off 감지.....
        screenReceiver = object: BroadcastReceiver() {
            //onReceive() 가 자동 호출되면서.. 두번째 매개변수로.. 리시버를 실행시킨 intent 객체 전달..
            override fun onReceive(context: Context?, intent: Intent?) {
                //intent 의 액션 문자열 획득..
                when(intent?.action){
                    Intent.ACTION_SCREEN_ON -> Log.d("kkang", "screen on...")
                    Intent.ACTION_SCREEN_OFF -> Log.d("kkang", "screen off.....")
                }
            }
        }

        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_SCREEN_ON)
        filter.addAction(Intent.ACTION_SCREEN_OFF)

        registerReceiver(screenReceiver, filter)

    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(screenReceiver)
    }
}