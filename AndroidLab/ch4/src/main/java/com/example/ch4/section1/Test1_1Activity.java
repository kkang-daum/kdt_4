package com.example.ch4.section1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ch4.R;
import com.example.ch4.databinding.ActivityTest11Binding;

public class Test1_1Activity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    ActivityTest11Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityTest11Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //test1.... 이벤트 핸들러를 독립 클래스로 작성해서.. 지정......
        //두 뷰의 이벤트 핸들러를 동일한 클래스 객체로 지정해도 되고.. 따로 만들어서 지정해도 되고..
        binding.button1.setOnClickListener(new EventHandler());
        binding.button2.setOnClickListener(new EventHandler());

        //test2...... 액티비티 자체가 이벤트 핸들러가 되게...
        binding.check.setOnCheckedChangeListener(this);

        //test3.....이벤트 핸들러가 어디선가 재사용이 되지 않는다고 하면.. 익명 클래스로..
//        binding.switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                Log.d("kkang", "switch event "+isChecked);
//            }
//        });
        //인터페이스를 구현한 익명클래스를 정의하고 지정할대.. 그 인터페이스의 추상함수가 하나라면..
        //아래처럼 줄여서 작성..
        binding.switch1.setOnCheckedChangeListener((CompoundButton buttonView, boolean isChecked) -> {
            Log.d("kkang", "switch event "+isChecked);
        });

        //==>코드 스타일을 어떻게 작성하든(test1, test2, test3) 이벤트 핸들러는 지정된 인터페이스를 구현해야 한다.
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Log.d("kkang", "check event "+isChecked);
    }

    //button 의 이벤트 핸들러 클래스..
    class EventHandler implements View.OnClickListener {
        //동일한 핸들러를 여러 뷰에 등록한 경우.. 모두 아래의 함수가 호출된다..
        //현재 어떤 뷰의 이벤트가 발생한 것인지를 식별해서 프로그램 작성해야 한다..
        //이벤트 콜백 함수의 매개변수가 현재 이벤트가 발생한 객체이다..
        @Override
        public void onClick(View v) {
            if(v == binding.button1){
                //런타임 로그.. 로그캣 창에.. 너무 많은 로그가 남아서.. 로그를 식별하기 위해서 tag 명을 같이 준다
                Log.d("kkang", "button1 click");
            }else if(v == binding.button2){
                Log.d("kkang", "button2 click");
            }
        }
    }
}