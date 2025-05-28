package com.example.ch3.section1

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch3.R
import com.example.ch3.databinding.ActivityTest12TwoBinding

//동일 ViewModel 을 두개의 Activity 에서 이용하고 있다...

class Test1_2TwoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityTest12TwoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val viewModel: MyViewModel2 by viewModels()

        binding.button1.setOnClickListener {
            viewModel.count++
        }

        binding.button2.setOnClickListener {
            Toast.makeText(this, "two :  ${viewModel.count}",
                Toast.LENGTH_SHORT).show()
        }
    }
}