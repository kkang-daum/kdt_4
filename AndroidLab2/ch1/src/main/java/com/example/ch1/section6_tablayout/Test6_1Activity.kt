package com.example.ch1.section6_tablayout

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch1.OneFragment
import com.example.ch1.R
import com.example.ch1.TwoFragment
import com.example.ch1.databinding.ActivityTest61Binding
import com.google.android.material.tabs.TabLayout

class Test6_1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityTest61Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.tabs1.run {
            val tab1: TabLayout.Tab = newTab()//tab button
            tab1.text = "Tab1"
            addTab(tab1)

            val tab2: TabLayout.Tab = newTab()//tab button
            tab2.text = "Tab2"
            addTab(tab2)

            val tab3: TabLayout.Tab = newTab()//tab button
            tab3.text = "Tab3"
            addTab(tab3)
        }

        //tab button 이벤트 처리..
        binding.tabs1.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            //새로운 탭 버튼이 클릭된 순간..
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val transaction = supportFragmentManager.beginTransaction()
                when(tab?.text){//현재 선택된 탭 버튼의 문자열..
                    "Tab1" -> transaction.replace(R.id.container1, OneFragment())
                    "Tab2" -> transaction.replace(R.id.container1, TwoFragment())
                    "Tab3" -> transaction.replace(R.id.container1, TwoFragment())
                }
                transaction.commit()
            }
            //선택된 탭 버튼이 다시 클릭된 순간..
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
            //선택이 해제되는 순간..
            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

        })
    }
}