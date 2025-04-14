package com.example.androidproject;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.androidproject.databinding.ActivityScoreAddBinding;
import com.example.androidproject.db.DBHelper;

public class ScoreAddActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityScoreAddBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityScoreAddBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.key0.setOnClickListener(this);
        binding.key1.setOnClickListener(this);
        binding.key2.setOnClickListener(this);
        binding.key3.setOnClickListener(this);
        binding.key4.setOnClickListener(this);
        binding.key5.setOnClickListener(this);
        binding.key6.setOnClickListener(this);
        binding.key7.setOnClickListener(this);
        binding.key8.setOnClickListener(this);
        binding.key9.setOnClickListener(this);
        binding.keyBack.setOnClickListener(this);
        binding.keyAdd.setOnClickListener(this);
    }

    //여러 뷰의 이벤트를 하나의 함수에서 모두 처리.. 매개변수가 현재 이벤트가 발생한 객체..
    //매개변수로 식별해서....
    @Override
    public void onClick(View v) {
        if(v == binding.keyAdd){
            //DetailActivity 에 의해 이 액티비티가 실행되었을 것이다..
            //DetailActivity 가 전달해준.. 학생 id 값을 추출한다..
            Intent intent = getIntent();
            int id = intent.getIntExtra("id", 0);

            long date = System.currentTimeMillis();

            String score = binding.keyEdit.getText().toString();

            DBHelper helper = new DBHelper(this);
            SQLiteDatabase db = helper.getWritableDatabase();
            db.execSQL("insert into tb_score (student_id, date, score) values (?,?,?)",
                    new String[]{String.valueOf(id), String.valueOf(date), score});
            db.close();

            //화면은?????
            //CRUD 의 create 화면이다.. 목록 화면으로 자동 전환..DetailActivity... 데이터 넘기면서..
            intent.putExtra("score", score);
            intent.putExtra("date", date);
            setResult(RESULT_OK, intent);
            finish();
        }else if(v == binding.keyBack){
            //현재 유저가 입력한 score 획득..
            String score = binding.keyEdit.getText().toString();
            if(score.length() == 1){
                binding.keyEdit.setText("0");
            }else {
                //index 0 부터.. length() -1까지 잘라서..
                String newScore = score.substring(0, score.length() - 1);
                binding.keyEdit.setText(newScore);
            }
        }else {
            //나머지 숫자키....
            //버튼의 문자열이 숫자이다..
            //이벤트가 발생한 버튼의 문자열을 추출해서.. 입력하는 숫자로 사용..

            //현재 이벤트가 발생한 버튼 객체..
            Button btn = (Button) v;
            String txt = btn.getText().toString();

            String score = binding.keyEdit.getText().toString();
            if(score.equals("0")){
                //현재 화면에 0이 찍혀 있다면.. 유저가 클릭한 문자열을 그대로 출력..
                binding.keyEdit.setText(txt);
            }else {
                String newScore = score + txt;
                //숫자로 계산하려고.. int 타입으로 변형..
                int intScore = Integer.parseInt(newScore);
                if(intScore > 100){
                    Toast.makeText(this, R.string.read_add_score_over, Toast.LENGTH_SHORT)
                            .show();
                }else {
                    binding.keyEdit.setText(newScore);
                }
            }
        }
    }
}