package com.example.androidproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.androidproject.adapter.MainAdapter;
import com.example.androidproject.callback.DialogCallback;
import com.example.androidproject.databinding.ActivityMainBinding;
import com.example.androidproject.db.DBHelper;
import com.example.androidproject.model.Student;
import com.example.androidproject.util.DialogUtil;
import com.example.androidproject.util.PermissionUtil;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    //AddStudentActivity 실행시키고.. 되돌아 올때 callback 실행..
    ActivityResultLauncher<Intent> addLauncher;

    //항목구성 데이터..
    ArrayList<Student> datas = new ArrayList<>();//초기는 빈 상태..
    MainAdapter adapter;

    ActivityResultLauncher<Intent> detailLauncher;

    //back button 누른 시간..
    long initTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Toolbar 는 ActionBar 를 대체
        //자동으로..actionbar 에 나올 내용(title, navigation icon, menu)이 toolbar 에 나오지는 않는다.
        //코드에서 한줄 명령은 내려야 한다.. toolbar 가 개발자 뷰임으로.. 어느 뷰에.. actionbar 내용이
        //나오면 된다고..
        setSupportActionBar(binding.toolbar);

        addLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    //intent 의 extra 데이터로 넘어온 결과 데이터 추출..
                    Intent intent = result.getData();
                    int id = (int)intent.getLongExtra("id", 0);
                    String name = intent.getStringExtra("name");
                    String email = intent.getStringExtra("email");
                    String phone = intent.getStringExtra("phone");
                    String memo = intent.getStringExtra("memo");
                    String photo = intent.getStringExtra("photo");

                    Student student = new Student(id, name, email, phone, memo, photo);
                    //새로운 Student 객체가 만들어졌다.. 이 데이터로 항목이 하나 추가되게 하면 된다..
                    //adapter에 넘긴 항목 구성 데이터에 추가한 후에..
                    //변경사항 반영해.. 명령내리면 된다..
                    datas.add(student);
                    adapter.notifyDataSetChanged();
                }
        );

        PermissionUtil.checkAllPermission(this, isAllGranted -> {
            if(isAllGranted){
                makeRecyclerView();
            }else {
                showDialog();
            }
        });


        detailLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                        //update...
                    //adapter.notifyDataSetChanged()
                }
        );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        //SearchView......................
        MenuItem menuItem = menu.findItem(R.id.menuMainSearch);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("학생 이름으로 검색하세요");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //검색을 위해 유저가.. 키보드의 검색버튼을 클릭한 순간..
            @Override
            public boolean onQueryTextSubmit(String query) {
                //검색 결과를 출력하기 위한 데이터가 다시 구축이 되어야 해서...
                //검색어를 전달해서.. 그 검색어로 db select 하게만 처리..
                getListData(query);
                //이미 화면에 recyclerview 의 adapter 가 작업을 해서.. 목록이 출력된 상태이다.
                //검색 결과로 목록 데이터를 변경했다.. 변경 명령 내려야 화면에 반영된다..
                adapter.notifyDataSetChanged();
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menuMainAdd){
            //화면전환.. 되돌아 올때.. 사후처리..
            Intent intent = new Intent(this, AddStudentActivity.class);
            addLauncher.launch(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    //db data select.....
    private void getListData(String query){

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor;
        if(query != null){
            //검색...
            cursor = db.rawQuery("select * from tb_student where name=? order by name", new String[]{query});
        }else {
            //최초....
            cursor = db.rawQuery("select * from tb_student order by name", null);
        }
        //기존 데이터 제거 후에.. 새로운 데이터로 목록 구성..
        datas.clear();


        while(cursor.moveToNext()){
            Student student = new Student();
            student.setId(cursor.getInt(0));
            student.setName(cursor.getString(1));
            student.setEmail(cursor.getString(2));
            student.setPhone(cursor.getString(3));
            student.setPhoto(cursor.getString(4));
            student.setMemo(cursor.getString(5));

            datas.add(student);
        }
    }

    private void makeRecyclerView(){
        getListData(null);
        adapter = new MainAdapter(this, datas);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL
        ));
    }

    private void showDialog(){
        DialogUtil.showMessageDialog(this, getString(R.string.permission_denied),
                "확인", null, new DialogCallback() {
                    @Override
                    public void onPositiveCallback() {

                    }

                    @Override
                    public void onNegativeCallback() {

                    }
                });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(System.currentTimeMillis() - initTime > 3000) {
                Toast.makeText(this, "종료하려면 한번 더 누르세요", Toast.LENGTH_SHORT).show();
                initTime = System.currentTimeMillis();
                return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }
}