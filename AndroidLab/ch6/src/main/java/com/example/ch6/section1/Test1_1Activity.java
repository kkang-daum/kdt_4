package com.example.ch6.section1;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ch6.R;
import com.example.ch6.databinding.ActivityTest11Binding;

import java.util.ArrayList;
import java.util.HashMap;

public class Test1_1Activity extends AppCompatActivity {

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

        //데이터 획득..
        String[] arrayData = getResources().getStringArray(R.array.location);

        //항목 하나에 데이터 하나 출력...
        ArrayAdapter arrayAdapter
                = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayData);
        binding.list1.setAdapter(arrayAdapter);
        //항목 선택 이벤트 추가..
        binding.list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //position : 선택한 항목의 index...
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Test1_1Activity.this, arrayData[position], Toast.LENGTH_SHORT)
                        .show();
            }
        });

        //SimpleAdapter..................... 한항목에 여러건의 데이터가 순차적으로..........
        //SimpleAdapter 에 데이터 구성이 ArrayList<Map<String, String>> 으로 준비..
        ArrayList<HashMap<String, String>> simpleData = new ArrayList<>();
        for(int i = 0; i < arrayData.length; i++){
            HashMap<String, String> map = new HashMap<>();
            map.put("name", arrayData[i]);
            map.put("content", String.valueOf(i * 10));
            simpleData.add(map);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(
          this,
          simpleData,//항목을 구성하기 위한 데이터..
          android.R.layout.simple_list_item_2,//항목을 구성하기 위한 layout xml
          new String[]{"name", "content"},//항목의 데이터가 Map, Map 에서 데이터를 추출할 때 사용할 key
          new int[]{android.R.id.text1, android.R.id.text2}//layout xml 에 항목 데이터를 출력할 뷰의 id
        );
        binding.list2.setAdapter(simpleAdapter);
    }
}