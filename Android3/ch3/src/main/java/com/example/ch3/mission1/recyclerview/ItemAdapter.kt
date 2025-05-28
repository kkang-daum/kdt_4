package com.example.ch3.mission1.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.example.ch3.databinding.ItemMainBinding
import com.example.ch3.mission1.model.Item


class ItemAdapter (val context: Context, val datas: List<Item>): RecyclerView.Adapter<ItemViewHolder>() {

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = datas[position]
    }


}