package com.example.ch8.section2;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
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
        //코드에서 메뉴를 구성하는 경우......
//        menu.add(0, 0, 0, "menu1");
//        menu.add(0, 1, 0, "menu2");

        //resource xml 로 메뉴 구성..
        //아래의 한줄만으로.. 메뉴는 화면에 나온다..
        getMenuInflater().inflate(R.menu.menu_test2, menu);
        //아래는 SearchView 때문에....
        //SearchView 가 등록된 MenuItem 을 먼저 획득하고.. 그 안에 등록된 ActionView 를 획득..
        MenuItem menuItem = menu.findItem(R.id.menu3);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("hint");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //검색을 위해 유저가.. 키보드의 검색버튼을 클릭한 순간..
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(Test2_1Activity.this, query, Toast.LENGTH_SHORT).show();
                return false;
            }
            //검색어 한자한자.. 입력시마다..
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    //메뉴 이벤트 함수.....매개변수가 현재 이벤트가 발생한 메뉴 아이템 객체..
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu1){
            Toast.makeText(this, "menu1 click", Toast.LENGTH_SHORT).show();
        }else if(item.getItemId() == R.id.menu2){
            Toast.makeText(this, "menu2 click", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}