package com.example.ch3.section1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch3.R
import com.example.ch3.databinding.ActivityTest12OneBinding

//ViewModel 객체는.. Activity Scope 내에서 Singletone 이다..
//여러 Activity 가 동일 ViewModel 을 이용하면 Activity 단위로 객체 생성된다..

class Test1_2OneActivity : AppCompatActivity() {

    var activityCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityTest12OneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val viewModel: MyViewModel2 by viewModels()

        binding.button1.setOnClickListener {
            activityCount++
            viewModel.count++
        }

        binding.button2.setOnClickListener {
            Toast.makeText(this, "one : $activityCount, ${viewModel.count}",
                Toast.LENGTH_SHORT).show()
        }

        binding.button3.setOnClickListener {
            startActivity(Intent(this, Test1_2TwoActivity::class.java))
        }
    }
}