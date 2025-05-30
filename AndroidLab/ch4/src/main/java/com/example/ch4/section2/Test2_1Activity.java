package com.example.ch4.section2;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ch4.R;

public class Test2_1Activity extends AppCompatActivity {

    float initX;

    long initTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test21);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //onBackPressed() 함수를 deprecated 시키면서 새롭게 제시한 방법.. callback 함수만 등록해라..
        //알아서 호출해 줄게..
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Log.d("kkang", "handleOnBackPressed");
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            initX = event.getRawX();
        }else if(event.getAction() == MotionEvent.ACTION_UP){
            float diffX = initX - event.getRawX();
            if(diffX > 30){
                Log.d("kkang", "왼쪽으로 화면을 밀었습니다.");
            }else if(diffX < -30){
                Log.d("kkang", "오른쪽으로 화면을 밀었습니다.");
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_VOLUME_UP){
            Log.d("kkang", "volume up key pressed");
        }
//        else if(keyCode == KeyEvent.KEYCODE_BACK){
//            if(System.currentTimeMillis() - initTime > 3000) {
//                Toast.makeText(this, "종료하려면 한번 더 누르세요", Toast.LENGTH_SHORT).show();
//                initTime = System.currentTimeMillis();
//                return true;
//            }
//
//        }
        return super.onKeyDown(keyCode, event);
    }

    //만약 back button 만 이벤트 처리를 목적으로 한다면 onKeyDown 에서 처리하지 않고.. 아래의 함수를 이용할 수도
    //아래의 api 는 deprecated 되었다..
    //여전히 api 를 지원하지만..사용하지 않았으면 좋겠다고 권고받은 상태..
    //새로운 대체 방법이 제시되거나.. 내부적으로 문제가 있거나..
//    @Override
//    public void onBackPressed() {
//        Log.d("kkang", "onBackPressed");
//        super.onBackPressed();
//    }
}