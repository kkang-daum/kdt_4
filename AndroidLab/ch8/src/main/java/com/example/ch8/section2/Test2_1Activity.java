package com.example.ch8.section2;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ch8.R;

public class Test2_1Activity extends AppCompatActivity {

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
    }

    //액티비티의 메뉴 구성 함수, 액티비티 초기화 되면서 한번 호출..
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "menu1");
        menu.add(0, 1, 0, "menu2");
        return super.onCreateOptionsMenu(menu);
    }
    //메뉴 이벤트 함수.....매개변수가 현재 이벤트가 발생한 메뉴 아이템 객체..
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == 0){
            Toast.makeText(this, "menu1 click", Toast.LENGTH_SHORT).show();
        }else if(item.getItemId() == 1){
            Toast.makeText(this, "menu2 click", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}