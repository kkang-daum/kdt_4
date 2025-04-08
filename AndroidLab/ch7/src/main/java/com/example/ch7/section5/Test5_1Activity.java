package com.example.ch7.section5;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.os.VibratorManager;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ch7.R;
import com.example.ch7.databinding.ActivityTest51Binding;

public class Test5_1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        ActivityTest51Binding binding = ActivityTest51Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.button1.setOnClickListener(view -> {
            //진동을 울리기 위한 시스템 서비스 획득, 버전 차이가 있다..
            Vibrator vibrator;
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
                VibratorManager manager = (VibratorManager) getSystemService(VIBRATOR_MANAGER_SERVICE);
                vibrator = manager.getDefaultVibrator();
            }else {
                vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            }

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                vibrator.vibrate(VibrationEffect.createOneShot(1000,
                        VibrationEffect.DEFAULT_AMPLITUDE));
            }else {
                vibrator.vibrate(1000);
            }
        });

        binding.button2.setOnClickListener(view -> {
            //시스템의 기본 효과음 식별자 획득...
            Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            //이 uri 를 플레이 할수 있는 객체..
            Ringtone ringtone = RingtoneManager.getRingtone(this, uri);
            ringtone.play();
        });
    }
}