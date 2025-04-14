package com.example.androidproject.adapter;

import android.app.Activity;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproject.databinding.ItemDetailBinding;

import java.util.ArrayList;
import java.util.Map;

class DetailViewHolder extends RecyclerView.ViewHolder {
    ItemDetailBinding binding;
    DetailViewHolder(ItemDetailBinding binding){
        super(binding.getRoot());
        this.binding = binding;
    }
}
public class DetailAdapter extends RecyclerView.Adapter<DetailViewHolder>{
    ArrayList<Map<String, String>> datas;//항목 구성 데이터.. activity 가 전달할 거다..
    Activity context;

    public DetailAdapter(Activity context, ArrayList<Map<String, String>> datas){
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @NonNull
    @Override
    public DetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDetailBinding binding = ItemDetailBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new DetailViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailViewHolder holder, int position) {
        //항목 하나의 데이터 획득..
        Map<String, String> score = datas.get(position);
        holder.binding.detailItemScore.setText(score.get("score"));
        holder.binding.detailItemDate.setText(score.get("date"));
    }
}
