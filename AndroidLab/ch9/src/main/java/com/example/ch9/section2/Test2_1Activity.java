package com.example.ch9.section2;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ch9.R;
import com.example.ch9.databinding.ActivityTest21Binding;

public class Test2_1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        ActivityTest21Binding binding = ActivityTest21Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ActivityResultLauncher<Intent> launcher = registerForActivityResult(
                //요청의 실행자... 퍼미션(RequestPermission), 인텐트 발생(StartActivityForResult)
                new ActivityResultContracts.StartActivityForResult(),
                //callback...
                (result) -> {
                    //넘어온 데이터 획득..
                    Intent intent = result.getData();
                    binding.resultView.setText(intent.getStringExtra("result"));
                }
        );

        binding.button.setOnClickListener(view -> {
            //intent 발생.. extra 데이터 포함시켜서..
            Intent intent = new Intent(this, SomeActivity.class);
            intent.putExtra("data1", "hello");
            intent.putExtra("data2", 100);
            //되돌아 와서 사후처리를 목적으로 한다.. startActivity() 가 아니라.. launcher 에 일을 시켜서
            launcher.launch(intent);
        });
    }
}