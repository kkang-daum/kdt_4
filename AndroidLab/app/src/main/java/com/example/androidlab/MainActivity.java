package com.example.androidlab;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //액티비티의 background 가 시스템 영역(status bar, navigation bar) 까지 나오게
        EdgeToEdge.enable(this);

        //화면 출력 명령...
        //R - 리소스...
        setContentView(R.layout.activity_main);

        //액티비티의 내용이 화면에 출력될때...
        //디바이스의 특징에 의해 보이지 않게 되는 부분을 최대한
        //살려서 보이게 하라..
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
}