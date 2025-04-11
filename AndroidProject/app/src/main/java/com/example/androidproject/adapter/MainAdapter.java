package com.example.androidproject.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproject.MainActivity;
import com.example.androidproject.databinding.ItemMainBinding;
import com.example.androidproject.model.Student;

import java.util.ArrayList;

class MainViewHolder extends RecyclerView.ViewHolder {
    ItemMainBinding binding;
    MainViewHolder(ItemMainBinding binding){
        super(binding.getRoot());
        this.binding = binding;
    }
}

public class MainAdapter extends RecyclerView.Adapter<MainViewHolder>{
    ArrayList<Student> datas;//항목 구성 데이터들.. activity 가 전달할 거다..
    Activity context;

    public MainAdapter(Activity context, ArrayList<Student> datas){
        this.datas = datas;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMainBinding binding = ItemMainBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new MainViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        Student student = datas.get(position);
        holder.binding.itemNameView.setText(student.getName());
    }
}
