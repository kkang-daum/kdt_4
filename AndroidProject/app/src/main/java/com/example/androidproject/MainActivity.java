package com.example.androidproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
                    int id = intent.getIntExtra("id", 0);
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


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    private void getListData(){
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from tb_student order by name", null);

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
        getListData();
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
}