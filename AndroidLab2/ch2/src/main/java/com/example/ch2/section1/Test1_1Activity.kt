package com.example.ch2.section1

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch2.R
import com.example.ch2.databinding.ActivityTest11Binding

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

        binding.saveBtn.setOnClickListener {
            //preference 객체 획득..
            //PRIVATE 이외에 READABLE, WRITEABLE 등이 있지만 해당 앱에서만 사용하는 PRIVATE 권장.
            val sharedPref = getSharedPreferences("my_prefs", MODE_PRIVATE)
            sharedPref.edit().run {
                putString("data1", "hello")
                putInt("data2", 10)
                apply()
            }

            //단일 액티비티의 데이터 영속화..
            val sharedPref2 = getPreferences(MODE_PRIVATE)
            sharedPref2.edit().run {
                putString("data1", "world")
                putInt("data2", 20)
                apply()
            }
        }

        binding.getBtn.setOnClickListener {
            val sharedPref = getSharedPreferences("my_prefs", MODE_PRIVATE)
            val data1 = sharedPref.getString("data1", "none")
            val data2 = sharedPref.getInt("data2", 0)
            binding.appResult.text = "$data1, $data2"

            val sharedPref2 = getPreferences(MODE_PRIVATE)
            val data3 = sharedPref2.getString("data1", "none")
            val data4 = sharedPref2.getInt("data2", 0)
            binding.activityResult.text = "$data3, $data4"
        }

        binding.goBtn.setOnClickListener {
            val intent = Intent(this, SomeActivity::class.java)
            startActivity(intent)
        }
    }
}