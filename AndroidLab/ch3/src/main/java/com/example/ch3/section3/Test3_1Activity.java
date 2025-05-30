package com.example.ch3.section3;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ch3.R;
import com.example.ch3.databinding.ActivityTest31Binding;

public class Test3_1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        ActivityTest31Binding binding = ActivityTest31Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.tab1.setOnClickListener(view -> {
            binding.tabContent1.setVisibility(TextView.VISIBLE);
            binding.tabContent2.setVisibility(TextView.INVISIBLE);
            binding.tabContent3.setVisibility(TextView.INVISIBLE);
        });
        binding.tab2.setOnClickListener(view -> {
            binding.tabContent1.setVisibility(TextView.INVISIBLE);
            binding.tabContent2.setVisibility(TextView.VISIBLE);
            binding.tabContent3.setVisibility(TextView.INVISIBLE);
        });
        binding.tab3.setOnClickListener(view -> {
            binding.tabContent1.setVisibility(TextView.INVISIBLE);
            binding.tabContent2.setVisibility(TextView.INVISIBLE);
            binding.tabContent3.setVisibility(TextView.VISIBLE);
        });
    }
}