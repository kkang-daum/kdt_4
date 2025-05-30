package com.example.ch3_outer

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch3_outer.databinding.ActivityOneBinding
import android.util.Log

class OneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityOneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.selfBtn.setOnClickListener {
            //standard 로 실행, 매번 객체 생성되고.. task 에 계속 추가..
//            val intent = Intent(this, OneActivity::class.java)
//            startActivity(intent)

            //singleTop, manifest 에 선언하는 것이 좀더 일반적이지만 테스트 편의성을 위해서..
            //코드에서 선언..
            //task 의 top 에 있는 경우에 한해서.. 다시 생성하지 않는다..
            val intent = Intent(this, OneActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }

    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        //singleTop 으로 선언된 경우, top 에 있으면 다시 생성하지 않는다.
        //단지 새로운 Intent 가 발생했어, 라는 상황을 인지할 수도 있어서.. 이 함수가 반복 호출..
        Log.d("kkang", "onNewIntent......")
    }
}