package com.example.ch3.section2

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.example.ch3.R
import com.example.ch3.databinding.ActivityTest21Binding

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

        val viewModel: MyViewModel by viewModels()

        viewModel.liveData.observe(this, object : Observer<Int> {
            override fun onChanged(value: Int) {
                binding.resultView.text = "$value"
            }
        })

        //LiveData... 주력으로 사용하는 곳은 ViewModel - Activity(Fragment)
        //필요한곳 아무곳에서나 이용이 가능..
        val myLiveData = MyLiveData()
        myLiveData.observe(this){
            Log.d("kkang", it)
        }

        binding.button.setOnClickListener {
            viewModel.changeCount()

            myLiveData.sayHello("kim")
        }
    }
}