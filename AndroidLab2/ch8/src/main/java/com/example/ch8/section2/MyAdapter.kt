package com.example.ch8.section2

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ch8.databinding.ItemBinding

class MyViewHolder(val binding: ItemBinding): RecyclerView.ViewHolder(binding.root)

//datas - activity 가 서버 연동을 통해 받은 서버 데이터..
class MyAdapter(val context: Context, val datas: MutableList<ItemModel>?)
    : RecyclerView.Adapter<MyViewHolder>() {

    override fun getItemCount(): Int {
        return datas?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ItemBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val binding = holder.binding
        val model = datas!![position]

        //서버에서 받은 데이터를 화면에 출력..
        binding.itemTitle.text = model.title
        binding.itemDesc.text = model.description
        binding.itemTime.text = "${model.author} At ${model.publishedAt}"
        //서버에서 이미지 다운로드 url 이 전달되었다.. url 을 문자열로 출력할 수는 없다..
        //url 을 이용해 이미지를 다운로드 받아 화면에 출력해야 한다..
        //Volley API 이용해서 이미지 다운로드 가능하지만.. Glide API 사용하겠다..
        Glide.with(context)
            .load(model.urlToImage)
            .into(binding.itemImage)



    }
}