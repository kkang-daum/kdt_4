package com.example.ch5.section2

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch5.R
import com.example.ch5.databinding.ActivityTest21Binding

class Test2_1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityTest21Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.backgroundBtn.setOnClickListener {
            //서비스를 foreground service 로 구동...
            //startForegroundService() 로 실행시키면 서비스는 구동은 된다.. 백그라운드 상황에서도
            //얼마후... android.app.RemoteServiceException$ForegroundServiceDidNotStartInTimeException: Context.startForegroundService() did not then call Service.startForeground(): ServiceRecord{5ad0cfa u0 com.example.ch5/.section2.MyService2 c:com.example.ch5}
            //에러 발생..
            val serviceIntent = Intent(this, MyService2::class.java)
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                startForegroundService(serviceIntent)
            }else {
                startService(serviceIntent)
            }
        }
    }
}