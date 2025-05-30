package com.example.ch4.section1

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch4.R
import com.example.ch4.databinding.ActivityTest11Binding

class Test1_1Activity : AppCompatActivity() {

    lateinit var receiver: BroadcastReceiver

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
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

        binding.staticBtn.setOnClickListener {
            val intent = Intent(this, MyReceiver::class.java)
            sendBroadcast(intent)
        }

        //코드에서 동적 등록.. receiver 가 항상 실행될 필요는 없고..
        //특정 상황, 특정 컴포넌트가 실행상태에서만 실행되어야 하는 경우...
        receiver = object : BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                Log.d("kkang", "dynamic receiver...........")
            }
        }
        //코드에서 동적 등록..
        val filter = IntentFilter("com.example.ch4.MY_ACTION")
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            registerReceiver(receiver, filter, Context.RECEIVER_EXPORTED)
        }else {
            registerReceiver(receiver, filter)
        }

        binding.dynamicBtn.setOnClickListener {
            val intent = Intent("com.example.ch4.MY_ACTION")
            sendBroadcast(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        //코드에서 동적 등록시킨 리시버는 어느 순간 실행될 필요가 없다는 의미이다..
        //동적 등록 해제...
        //액티비티, 서비스등의 onCreate() 에서 등록.. onDestory() 에서 해제..
        unregisterReceiver(receiver)
    }
}