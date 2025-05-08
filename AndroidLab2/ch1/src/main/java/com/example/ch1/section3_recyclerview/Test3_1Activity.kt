package com.example.ch1.section3_recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.ch1.R
import com.example.ch1.databinding.ActivityTest31Binding
import com.example.ch1.databinding.RecyclerItemBinding

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

        val data = mutableListOf<String>()
        for(i in 1..10){
            data.add("Item $i")
        }

        binding.main.adapter = MyAdapter(data)

        //linear..............세로방향 나열..........
//        binding.main.layoutManager = LinearLayoutManager(this)

        //linear, 가로방향.....
//        val layoutManager = LinearLayoutManager(this)
//        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        //gridlayout
//        val layoutManager = GridLayoutManager(this, 2)

        //gridlayout, vertical, true - 아래부터 항목 나열..
//        val layoutManager = GridLayoutManager(this, 2,
//            GridLayoutManager.VERTICAL, true)

        //gridlayout, horizontal, false
//        val layoutManager = GridLayoutManager(this, 2,
//            GridLayoutManager.HORIZONTAL, false)

        //staggered.....................
        data.add("aaaaaaaaaa aaaaaaaaaaaaa, aaaa, aaaaaaaaaaaaaa, aaaaaaaaaaaa, aaaaaaaaaaaaaa, aaaaaaaaaaaa, aaaaaaaaaaaaaaaaa, aaaaaaaaaaaaaaaaa")
        data.add("bbbbbbbbbb bbbbbbbbbbb")
        data.add("ccccccccc")
        data.add("dddddddddddddd ddddddddddddddd dddddddddddddddd ddddddddddddddddd ddddddddddddddddd ddddddddddddddd ddddddddddddddddd ddddddddddddddddddd")
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        binding.main.layoutManager = layoutManager



    }
}

class MyViewHolder(val binding: RecyclerItemBinding): RecyclerView.ViewHolder(binding.root)

class MyAdapter(val data: MutableList<String>): RecyclerView.Adapter<MyViewHolder>(){
    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(RecyclerItemBinding.inflate(LayoutInflater.from(parent.context),
            parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val binding = holder.binding
        binding.itemData.text = data[position]
    }
}