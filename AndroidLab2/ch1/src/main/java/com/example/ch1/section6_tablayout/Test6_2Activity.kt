package com.example.ch1.section6_tablayout

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch1.R
import com.example.ch1.databinding.ActivityTest62Binding
import com.example.ch1.section2_viewpager.MyFragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator

class Test6_2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityTest62Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //viewpager... adapterview...
        binding.pager.adapter = MyFragmentStateAdapter(this)

        //tablayout 과 viewpager 연동... 어느 뷰인지는 지정해 줘야 한다..
        TabLayoutMediator(binding.tabs, binding.pager) { tab, position ->
            //매개변수로 지정한 이 함수가.. binding.pager 에 지정한 viewpager 의 화면 갯수 만큼 호출
            //pager 의 화면 갯수 맘큼 버튼이 나오면 된다..
            tab.text = "Tab ${position+1}"
        }.attach()
    }
}