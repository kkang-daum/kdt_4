package com.example.ch3.section1

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.ch3.R
import com.example.ch3.databinding.ActivityTest11Binding

//ViewModel 이용 방법..................

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

        //case1 - ViewModel 객체 선언....
        //관심의 분리는 맞지만.. ViewModel 을 이용한 Activity 상태 유지는 불가능..
        //Activity 상태 유지가 되려면 ViewModel 의 lifecycle 이 Activity 와 다르게 되어야 하는데
        //코드에서 직접 생성함으로.. 매번 생성되어서..
//        val viewModel = MyViewModel1()

        //case2 - ViewModelProvider
        //직접생성하지 않았다..
        //관심 분리, 액티비티 상태 유지 가능하다..
        //아래의 코드에 의해 ViewModel 이 매번 생성되지는 않는다.. 싱글톤으로 운영한다..
//        val viewModel = ViewModelProvider(this).get(MyViewModel1::class.java)


        //case3 - delegate 이용....
        //ViewModelProvider 의 축약형이다.. 내부적으로.. ViewModelProvider 이용한다..
        val viewModel: MyViewModel1 by viewModels()

        binding.button.setOnClickListener {
            Toast.makeText(this, "${viewModel.data}, ${viewModel.someFun()}",
                Toast.LENGTH_SHORT).show()
        }
    }
}