package com.example.ch3.section3

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch3.R
import com.example.ch3.databinding.ActivityTest31Binding

class Test3_1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityTest31Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.button.setOnClickListener {
            startActivity(Intent(this, SomeActivity::class.java))
        }

        //lifecycle observer 이자.. livedata
        val lifecycleObserver = MyActivityLifecycleObserver()
        lifecycleObserver.observe(this){
            binding.resultView.text = "$it"
        }
        lifecycle.addObserver(lifecycleObserver)
    }
}