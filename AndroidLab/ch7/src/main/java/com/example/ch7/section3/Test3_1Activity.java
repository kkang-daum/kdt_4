package com.example.ch7.section3;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ch7.R;
import com.example.ch7.databinding.ActivityTest31Binding;

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

        //dialog 의 버튼 클릭, 이벤트 핸들러..
        //이벤트 핸들러를 등록하지 않아도 dialog 버튼을 클릭하면 자동으로 닫긴다..
        //View.OnClickListener... View 에서 사용하는 것과 차이가 있다..
        //DialogInterface.OnClickListener
        DialogInterface.OnClickListener dialogHandler = new DialogInterface.OnClickListener() {
            //which - 버튼의 종류 식별,
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String message = "";
                if(which == DialogInterface.BUTTON_POSITIVE){
                    message = "positive button click";
                }else if(which == DialogInterface.BUTTON_NEGATIVE){
                    message = "negative button click";
                }else {
                    message = "neutral button click";
                }
                Toast.makeText(Test3_1Activity.this, message, Toast.LENGTH_SHORT)
                        .show();
            }
        };

        binding.button1.setOnClickListener(view -> {
            //AlertDialog 는 Builder 에 의해 생성.. Builder 를 먼저 준비하고..
            //setter 함수로 dialog 구성..
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(android.R.drawable.ic_dialog_info);
            builder.setTitle("title");
            builder.setMessage("message");
            //동일 함수로 버튼 추가가 중복되면.. 하나만 나온다..
            builder.setPositiveButton("OK", dialogHandler);
            builder.setPositiveButton("OK1", dialogHandler);
            builder.setNegativeButton("NO", dialogHandler);
            builder.setNeutralButton("MORE", dialogHandler);
            //builder.create() - AlertDialog 를 생성, show() 띄운다..
            builder.show();
        });
    }
}