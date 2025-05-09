package com.example.ch1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ch1.databinding.FragmentOneBinding
import com.example.ch1.section3_recyclerview.MyAdapter

class OneFragment: Fragment() {
    //자동 호출되는 라이프사이클 함수..
    //Fragment의 화면 구성을 목적으로 호출...
    //이 함수에서 리턴 시키는 뷰 객체가 Fragment 화면이다..
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentOneBinding.inflate(inflater, container, false)

        val datas = mutableListOf<String>()
        for(i in 1..30){
            datas.add("item $i")
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.adapter = MyAdapter(datas)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))

        return binding.root
    }
}