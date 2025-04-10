package com.example.ch9.section3;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ch9.R;
import com.example.ch9.databinding.ActivityTest31Binding;

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

        //퍼미션 띄우고.. 콜백 처리..
        ActivityResultLauncher<String> launcher = registerForActivityResult(
          new ActivityResultContracts.RequestPermission(),
          isGranted -> {
              if(isGranted){
                  Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:02-120"));
                  startActivity(intent);
              }else {
                  Toast.makeText(this, "permission denied..", Toast.LENGTH_SHORT)
                          .show();
              }
          }
        );

        binding.button1.setOnClickListener(view -> {
            if(ContextCompat.checkSelfPermission(this, "android.permission.CALL_PHONE")
                    == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:02-120"));
                startActivity(intent);
            }else {
                launcher.launch("android.permission.CALL_PHONE");
            }

        });

        binding.button2.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:37.566,127.977"));
            startActivity(intent);
        });
        binding.button3.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.naver.com"));
            startActivity(intent);
        });
    }
}