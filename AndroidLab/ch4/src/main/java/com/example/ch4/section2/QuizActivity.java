package com.example.ch4.section2;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ch4.R;
import com.example.ch4.databinding.ActivityQuizBinding;

public class QuizActivity extends AppCompatActivity {
    long initTime;

    //chronometer pause time 저장..
    long pauseTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        ActivityQuizBinding binding = ActivityQuizBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.startButton.setOnClickListener(view -> {
            binding.chronometer.setBase(SystemClock.elapsedRealtime() + pauseTime);
            binding.chronometer.start();

            binding.startButton.setEnabled(false);
            binding.stopButton.setEnabled(true);
            binding.resetButton.setEnabled(true);
        });
        binding.stopButton.setOnClickListener(view -> {
            pauseTime = binding.chronometer.getBase() - SystemClock.elapsedRealtime();
            binding.chronometer.stop();

            binding.startButton.setEnabled(true);
            binding.stopButton.setEnabled(false);
            binding.resetButton.setEnabled(true);
        });
        binding.resetButton.setOnClickListener(view -> {
            pauseTime = 0;
            binding.chronometer.setBase(SystemClock.elapsedRealtime());
            binding.chronometer.stop();

            binding.startButton.setEnabled(true);
            binding.stopButton.setEnabled(false);
            binding.resetButton.setEnabled(false);
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(System.currentTimeMillis() - initTime > 3000) {
                Toast.makeText(this, "종료하려면 한번 더 누르세요", Toast.LENGTH_SHORT).show();
                initTime = System.currentTimeMillis();
                return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }
}