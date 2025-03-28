package com.example.ch2.section1;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ch2.R;

public class Test1_1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        //test1...... 코드에서 직접 view 클래스 이용해서 화면 구성...
//        TextView nameView = new TextView(this);
//        nameView.setText("홍길동");
//
//        TextView emailView = new TextView(this);
//        emailView.setText("a@a.com");
//
//        //화면에 여러 뷰가 같이 나온다면.. 객체의 계층구조로 구성해서 출력해야 한다.
//        //디렉토리 같은 다른 객체를 add 시킬 뷰가 있어야 한다..
//        LinearLayout linearLayout = new LinearLayout(this);
//        linearLayout.setOrientation(LinearLayout.VERTICAL);
//        //사이즈 지정때문에 준비...
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.WRAP_CONTENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT
//        );
//
//        //디렉토리에 추가..
//        linearLayout.addView(nameView, params);
//        linearLayout.addView(emailView, params);
//        //화면 출력.. 매개변수에 뷰 계층의 root 객체를 지정
//        setContentView(linearLayout);
//
//
//        ViewCompat.setOnApplyWindowInsetsListener(linearLayout, (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });


        //layout xml 로 화면 구성..........................
        setContentView(R.layout.activity_test11);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}