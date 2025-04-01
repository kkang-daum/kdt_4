package com.example.ch4.section2;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ch4.R;
import com.example.ch4.databinding.ActivityQuiz1Binding;

public class Quiz1Activity extends AppCompatActivity {
    long pauseTime = 0;
    ActivityQuiz1Binding binding;
    long initTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityQuiz1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.btnStart.setOnClickListener(new EventHandler());
        binding.btnStop.setOnClickListener(new EventHandler());
        binding.btnReset.setOnClickListener(new EventHandler());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if(System.currentTimeMillis() - initTime > 3000){
                    Toast.makeText(Quiz1Activity.this, "종료하려면 한번 더 누르세요", Toast.LENGTH_SHORT).show();
                    initTime = System.currentTimeMillis();
                }else{
                    finish();
                }

            }
        });
    }

    class EventHandler implements View.OnClickListener {
        void Start(){
            binding.chronometer.setBase(SystemClock.elapsedRealtime() + pauseTime);
            binding.chronometer.start();

            binding.btnStart.setEnabled(false);
            binding.btnStop.setEnabled(true);
            binding.btnReset.setEnabled(true);
        }

        void Stop(){
            pauseTime = binding.chronometer.getBase() - SystemClock.elapsedRealtime();
            binding.chronometer.stop();

            binding.btnStart.setEnabled(true);
            binding.btnStop.setEnabled(false);
            binding.btnReset.setEnabled(true);
        }

        void Reset(){
            pauseTime = 0;
            binding.chronometer.setBase(SystemClock.elapsedRealtime());

            binding.btnStart.setEnabled(true);
            binding.btnStop.setEnabled(false);
            binding.btnReset.setEnabled(false);
        }

        @Override
        public void onClick(View v) {
            if(v == binding.btnStart){
                Start();
            }else if(v == binding.btnStop){
                Stop();
            }else{
                Reset();
            }
        }
    }


}