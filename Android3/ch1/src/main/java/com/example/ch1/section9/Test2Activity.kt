package com.example.ch1.section9

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.ch1.R
import com.example.ch1.databinding.ActivityTest2Binding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Test2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityTest2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.button3.setOnClickListener {
            finish()
        }
        binding.button2.setOnClickListener {
            //코루틴을 실행시키려면 먼저 scope 가 선언되어 있어야 한다..
            //- activity, fragment에서 CoroutineScope 를 구현해서.. 자체가 코루틴 스코프가 되거나..
            //- CoroutineScope() 함수를 이용해 스코프를 만들어 사용하거나..
            //액티비티, 프레그먼트 생존기간에만 동작해야 하는 코루프를 만들려면 액티비티 라이프사이클에서 신경..

            //이 부분을 도와 주기 위해서 안드로이드에서 추가된 scope 를 이용해서 개발..
            //액티비티, 프레그먼트, viewmodel 등과 동일 라이프사이클로 동작하는 scope...
            lifecycleScope.launch {
                repeat(5){
                    Log.d("kkang", "coroutine $it")
                    delay(1000)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("kkang", "activity onDestory")
    }
}