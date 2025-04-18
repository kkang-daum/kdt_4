package com.example.androidproject.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproject.DetailActivity;
import com.example.androidproject.MainActivity;

import com.example.androidproject.R;
import com.example.androidproject.callback.LauncherCallback;
import com.example.androidproject.databinding.ItemMainBinding;
import com.example.androidproject.model.Student;
import com.example.androidproject.util.BitmapUtil;
import com.example.androidproject.util.DialogUtil;

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

    LauncherCallback callback;

    public MainAdapter(Activity context, ArrayList<Student> datas, LauncherCallback callback){
        this.datas = datas;
        this.context = context;
        this.callback = callback;
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

        holder.binding.itemImageView.setOnClickListener(view -> {
            //이미지 상세보기로 가정.. 다이얼로그 띄운다..
            DialogUtil.showCustomDialog(context, R.drawable.ic_student_large);
        });

        holder.binding.itemCallView.setOnClickListener(view -> {
            if(ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) ==
                    PackageManager.PERMISSION_GRANTED){
                callPhone(student.getPhone());
            }else {
                DialogUtil.showToast(context, context.getString(R.string.permission_denied));
            }
        });
        holder.binding.itemNameView.setOnClickListener(view -> {
//            Intent intent = new Intent(context, DetailActivity.class);
//            //넘길 데이터는 있는가???? 있다.. 학생의 식별자 값...
//            intent.putExtra("id", student.getId());
//            //되돌아 왔을때 사후 처리가 있는가???? - 없다..
//            context.startActivity(intent);
            callback.launch();
        });

        Bitmap bitmap = BitmapUtil.getGalleryBitmapFromFile(context, student.getPhoto());
        if(bitmap != null)
            holder.binding.itemImageView.setImageBitmap(bitmap);
    }

    private void callPhone(String phoneNumber){
        if(phoneNumber != null && !phoneNumber.equals("")){
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phoneNumber));
            context.startActivity(intent);
        }else {
            DialogUtil.showToast(context, context.getString(R.string.main_list_phone_error));
        }
    }
}
