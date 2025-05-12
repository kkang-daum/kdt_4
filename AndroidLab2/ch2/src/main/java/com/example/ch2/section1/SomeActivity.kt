package com.example.ch2.section1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch2.R
import com.example.ch2.databinding.ActivitySomeBinding
import com.example.ch2.databinding.ActivityTest11Binding

class SomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivitySomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
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
    }
}