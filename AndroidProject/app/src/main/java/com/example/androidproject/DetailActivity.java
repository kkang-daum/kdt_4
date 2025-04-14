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
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.androidproject.adapter.DetailAdapter;
import com.example.androidproject.databinding.ActivityDetailBinding;
import com.example.androidproject.db.DBHelper;
import com.example.androidproject.model.Student;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;
    Student student;

    ArrayList<Map<String, String>> scoreList;//시험점수.. 목록 데이터.. db select 해서 준비.. adapter에게 전달
    DetailAdapter adapter;

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

        //초기 이 액티비티가 실행되면서..  db select 해서.. 시험점수를 목록으로 출력..
        setInitScoreData(id);

        //ScoreAddActivity 를 실행시키고자 한다.. 되돌아 왔을 때 사후처리가 필요한가?
        ActivityResultLauncher<Intent> addScoreLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    //ScoreAddActivity 에서 전달한 데이터 획득..
                    Intent intent = result.getData();
                    String score = intent.getStringExtra("score");
                    long date = intent.getLongExtra("date", 0);

                    HashMap<String, String> map = new HashMap<>();
                    map.put("score", score);
                    //데이터를 유저에게 뿌릴 문자열 포멧으로 변형해서..
                    Date d = new Date(date);
                    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
                    map.put("date", sd.format(d));

                    scoreList.add(map);
                    //새로운 항목 데이터가 추가된 것이다..
                    //adapter 가 이미 항목을 만들어 놓았을 것이다.. 변경사항이 있다고 알려준다..
                    adapter.notifyDataSetChanged();
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

    private void setInitScoreData(int id){
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        //tb_score 에서.. 이 학생(DetailActivity 에 출력된 학생)의 시험점수만.. 조건(where)
        Cursor c = db.rawQuery("select score, date from tb_score where student_id = ? order by date",
                new String[]{String.valueOf(id)});
        scoreList = new ArrayList<>();
        while (c.moveToNext()){
            HashMap<String, String> map = new HashMap<>();
            map.put("score", c.getString(0));
            Date d = new Date(Long.parseLong(c.getString(1)));
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
            map.put("date", sd.format(d));
            scoreList.add(map);
        }
        db.close();

        binding.detailRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DetailAdapter(this, scoreList);
        binding.detailRecyclerView.setAdapter(adapter);
        binding.detailRecyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
    }
}