package com.example.ch3.mission1.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ch3.R
import com.example.ch3.databinding.ItemMainBinding
import com.example.ch3.mission1.fragments.WebFragment
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

        holder.binding.run {
            itemTitle.text = item.title
            itemDesc.text = item.description
            itemTime.text = item.publishedAt

            visit.setOnClickListener {
                //WebFragment 실행..
                (context as AppCompatActivity).supportFragmentManager.beginTransaction().run {
                    replace(R.id.main, WebFragment(item.url))
                    addToBackStack(null)
                    commit()
                }
            }
            Glide.with(context)
                .load(item.urlToImage)
                .into(itemImage)
        }

    }


}