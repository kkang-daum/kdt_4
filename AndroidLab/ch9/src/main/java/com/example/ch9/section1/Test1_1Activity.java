package com.example.ch9.section1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ch9.R;
import com.example.ch9.databinding.ActivityTest11Binding;

public class Test1_1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        ActivityTest11Binding binding = ActivityTest11Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.button1.setOnClickListener(view -> {
            //Sub1Activity... 명시적...
            Intent intent = new Intent(this, Sub1Activity.class);
            startActivity(intent);
        });

        binding.button2.setOnClickListener(view -> {
            //Sub2Activity... 암시적....
            Intent intent = new Intent();
            intent.setAction("ACTION_SUB2");
            //android.intent.category.DEFAULT category 에 한해서는.. 정보를 주지 않아도 된다..
            //기본이 android.intent.category.DEFAULT category 에서 뒤지게 되어 있다..
            startActivity(intent);
        });

        binding.button3.setOnClickListener(view -> {
            //Sub3Activity를 암시적으로... category 정보 지정해서..
            Intent intent = new Intent();
            intent.setAction("ACTION_SUB3");
            intent.addCategory("com.example.ch9.CATEGORY_SUB3");
            startActivity(intent);
        });
        binding.button4.setOnClickListener(view -> {
            //Sub4Activity를 암시적으로 , data 정보 설정..
            Intent intent = new Intent("ACTION_SUB4", Uri.parse("http://www.naver.com"));
            startActivity(intent);
        });
    }
}