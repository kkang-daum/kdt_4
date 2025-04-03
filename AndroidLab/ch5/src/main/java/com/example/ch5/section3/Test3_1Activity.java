package com.example.ch5.section3;

import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowMetrics;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ch5.R;
import com.example.ch5.databinding.ActivityTest31Binding;

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

        //앱이 설치된 폰 사이즈 확인..
        //버전으로 식별해서.. 코드 작성..
        //Build.VERSION.SDK_INT - 현재 앱이 설치된 폰의 api level
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            WindowMetrics metrics = getWindowManager().getCurrentWindowMetrics();
            int width = metrics.getBounds().width();
            int height = metrics.getBounds().height();
            Log.d("kkang", width +":"+height);
        }else {
            Display display = getWindowManager().getDefaultDisplay();
            DisplayMetrics metrics = new DisplayMetrics();
            display.getRealMetrics(metrics);
            int width = metrics.widthPixels;
            int height = metrics.heightPixels;
            Log.d("kkang", width +":"+height);
        }

        //유저 폰의 밀도...
        float density = getResources().getDisplayMetrics().density;

        //코드에서 사이즈 지정할 때는 단위를 추가할 수 없다.
        //코드에서 지정하는 사이즈는 px 이다..
        // 폰사이즈 호환성을 고려해서 코드에서 사이즈 지정할 때, density 값을
        //얻어서.. 계산해서 추가..
        binding.button1.getLayoutParams().width = 100;
        binding.button2.getLayoutParams().width = (int)(100 * density);

    }
}