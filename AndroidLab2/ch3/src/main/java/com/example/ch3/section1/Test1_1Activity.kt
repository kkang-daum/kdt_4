package com.example.ch3.section1

import android.content.Intent
import android.os.Bundle
import android.provider.Telephony.Mms.Intents
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch3.R
import com.example.ch3.databinding.ActivityTest11Binding

class Test1_1Activity : AppCompatActivity() {

    var count = 0
    lateinit var binding: ActivityTest11Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityTest11Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.goBtn.setOnClickListener {
            val intent = Intent(this, SomeActivity::class.java)
            startActivity(intent)
        }

        binding.incrementBtn.setOnClickListener {
            count++
            binding.resultView.text = "$count"
        }

        Log.d("kkang", "onCreate....")
    }

    override fun onStart() {
        super.onStart()
        Log.d("kkang", "onStart....")
    }

    override fun onResume() {
        super.onResume()
        Log.d("kkang", "onResume....")
    }

    override fun onPause() {
        super.onPause()
        Log.d("kkang", "onPause....")
    }

    override fun onStop() {
        super.onStop()
        Log.d("kkang", "onStop....")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("kkang", "onDestory....")
    }

    //onDestory 직전에 호출.. 액티비티 상태 데이터 저장..
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("count", count)
    }

    //activity 의 상태 데이터가 있는 경우만 호출..
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        count = savedInstanceState.getInt("count")
        binding.resultView.text = "$count"
    }
}
//최초 실행.....
//onCreate....
//onStart....
//onResume....

//화면 전환.. 화면 이동...
//onPause....
//onStop....

//back 으로 되돌아 오면...
//onStart....
//onResume....

//화면 회전...
//onPause....
//onStop....
//onDestory....
//onCreate....
//onStart....
//onResume....