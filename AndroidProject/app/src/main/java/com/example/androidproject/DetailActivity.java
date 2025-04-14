package com.example.androidproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.androidproject.databinding.ActivityDetailBinding;
import com.example.androidproject.db.DBHelper;
import com.example.androidproject.model.Student;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;
    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //actionbar -> toolbar
        setSupportActionBar(binding.toolbar);

        //자신을 실행시키면서 전달한 데이터를 추출....
        int id = getIntent().getIntExtra("id", 0);

        //초기 데이터 화면에서 출력.. db select 해서...
        setInitStudentData(id);

        //ScoreAddActivity 를 실행시키고자 한다.. 되돌아 왔을 때 사후처리가 필요한가?
        ActivityResultLauncher<Intent> addScoreLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {

                }
        );

        binding.detailAddScoreBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, ScoreAddActivity.class);
            //넘길 데이터는 있는가?
            intent.putExtra("id", id);
            addScoreLauncher.launch(intent);
        });
    }

    private void setInitStudentData(int id){
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_student where _id=?",
                new String[]{String.valueOf(id)});

        //이전에.. 유저가 프로필사진 지정을 했다면.. 어느 사진인지.. 사진 파일 경로가 db 에 저장..
        String photoFilePath = null;

        if(cursor.moveToFirst()){
            //db data...
            String name = cursor.getString(1);
            String email = cursor.getString(2);
            String phone = cursor.getString(3);
            //화면 출력..
            binding.detailName.setText(name);
            binding.detailEmail.setText(email);
            binding.detailPhone.setText(phone);

            photoFilePath = cursor.getString(4);

            student = new Student(cursor.getInt(0), name, email, phone,
                    cursor.getString(5), photoFilePath);
        }

        db.close();
    }
}