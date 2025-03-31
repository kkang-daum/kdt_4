package com.example.ch2.section3;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ch2.R;

public class Test3_1Activity extends AppCompatActivity {

    Button visibleBtn;
    Button invisibleBtn;
    TextView targetView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        //화면출력.. inflate.. 아래의 코드만으로.. layout xml 의 뷰 객체 생성 완료
        setContentView(R.layout.activity_test31);
        //생성된 뷰 객체중 코드에서 이용하고자 하는 객체를 획득..
        visibleBtn = findViewById(R.id.visibleBtn);
        invisibleBtn = findViewById(R.id.invisibleBtn);
        targetView = findViewById(R.id.targetView);
        //이벤트에 의해 화면 제어..
        visibleBtn.setOnClickListener(view -> {
            targetView.setVisibility(TextView.VISIBLE);
        });
        invisibleBtn.setOnClickListener(view -> {
            targetView.setVisibility(TextView.INVISIBLE);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}