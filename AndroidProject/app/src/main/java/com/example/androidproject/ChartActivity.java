package com.example.androidproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.androidproject.databinding.ActivityChartBinding;
import com.example.androidproject.db.DBHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ChartActivity extends AppCompatActivity {
    ActivityChartBinding binding;
    //db select 해서.. chart data 구축...
    ArrayList<Map<String, String>> scoreList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityChartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //자신을 실행시킨 intent 에서 id(학생 식별자) 획득..
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);

        WebSettings settings = binding.webView.getSettings();
        settings.setJavaScriptEnabled(true);

        //db 에서 score select....
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.rawQuery("select score, date from tb_score where student_id=? order by date",
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


        binding.webView.loadUrl("file:///android_asset/test.html");

        binding.webView.addJavascriptInterface(new JavascriptTest(), "android");

    }

    class JavascriptTest {
        @JavascriptInterface
        public String getChartData(){
            StringBuffer buffer = new StringBuffer();
            buffer.append("[");
            Log.d("kkang", "111111111111111");
            int j = 0;
            for(int i = 0; i < scoreList.size() && j < 10; i++){
                buffer.append("["+j+",");//x축..
                buffer.append(scoreList.get(i).get("score"));//y축..
                buffer.append("]");
                if(i < scoreList.size() - 1) buffer.append(",");
                j++;
            }

            buffer.append("]");
            Log.d("kkang", buffer.toString());
            return buffer.toString();
        }
    }
}