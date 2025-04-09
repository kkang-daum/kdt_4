package com.example.ch9.section2;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ch9.R;
import com.example.ch9.databinding.ActivitySomeBinding;

public class SomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        ActivitySomeBinding binding = ActivitySomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //자신을 실행시킨 intent 객체 획득...
        Intent intent = getIntent();
        //넘어온 extra 데이터 추출...
        String data1 = intent.getStringExtra("data1");
        int data2 = intent.getIntExtra("data2", 0);
        binding.extraView.setText(data1+":"+data2);

        binding.button.setOnClickListener(view -> {
            //결과 데이터를 포함시켜서.. intent 에 extra로...
            intent.putExtra("result", "hello world");
            //상태 정보.. 표현.. 어떤 상태에서 되돌리는 것이다..
            setResult(RESULT_OK, intent);
            //코드적으로 되돌린다.. 자신의 액티비티를 종료시켜서.. 시스템에 의해서 이전 액티비티로
            //되돌아가게..
            finish();
        });
    }
}