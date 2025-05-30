package com.example.ch4.section2;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ch4.R;
import com.example.ch4.databinding.ActivityQuiz11Binding;

public class Quiz1_1Activity extends AppCompatActivity {

    ActivityQuiz11Binding binding;
    long pauseTime = 0, initTIme = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityQuiz11Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();

        binding.startBtn.setOnClickListener(v -> {
            binding.meter.setBase(SystemClock.elapsedRealtime() + pauseTime);
            binding.meter.start();
            binding.resetBtn.setEnabled(true);
            binding.stopBtn.setEnabled(true);
            binding.startBtn.setEnabled(false);
            //setBtnColor();
        });

        binding.stopBtn.setOnClickListener(v -> {
            pauseTime = binding.meter.getBase() - SystemClock.elapsedRealtime();
            binding.meter.stop();
            binding.stopBtn.setEnabled(false);
            binding.startBtn.setEnabled(true);
            binding.resetBtn.setEnabled(true);
            //setBtnColor();
        });

        binding.resetBtn.setOnClickListener(v -> {
            pauseTime = 0;
            binding.meter.setBase(SystemClock.elapsedRealtime());
            binding.resetBtn.setEnabled(false);
            binding.stopBtn.setEnabled(false);
            binding.startBtn.setEnabled(true);
            //setBtnColor();
        });
    }

    void init() {
        binding.startBtn.setEnabled(true);
        binding.stopBtn.setEnabled(false);
        binding.resetBtn.setEnabled(false);
        //setBtnColor();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - initTIme > 3000) {
                Toast.makeText(this, "종료하려면 뒤로가기를 한번 더 누르세요.", Toast.LENGTH_SHORT).show();
                initTIme = System.currentTimeMillis();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}