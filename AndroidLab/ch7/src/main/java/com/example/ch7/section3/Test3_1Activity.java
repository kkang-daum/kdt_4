package com.example.ch7.section3;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ch7.R;
import com.example.ch7.databinding.ActivityTest31Binding;
import com.example.ch7.databinding.DialogTestBinding;

import java.util.ArrayList;

public class Test3_1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        ActivityTest31Binding binding = ActivityTest31Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //dialog 의 버튼 클릭, 이벤트 핸들러..
        //이벤트 핸들러를 등록하지 않아도 dialog 버튼을 클릭하면 자동으로 닫긴다..
        //View.OnClickListener... View 에서 사용하는 것과 차이가 있다..
        //DialogInterface.OnClickListener
        DialogInterface.OnClickListener dialogHandler = new DialogInterface.OnClickListener() {
            //which - 버튼의 종류 식별,
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String message = "";
                if(which == DialogInterface.BUTTON_POSITIVE){
                    message = "positive button click";
                }else if(which == DialogInterface.BUTTON_NEGATIVE){
                    message = "negative button click";
                }else {
                    message = "neutral button click";
                }
                Toast.makeText(Test3_1Activity.this, message, Toast.LENGTH_SHORT)
                        .show();
            }
        };

        binding.button1.setOnClickListener(view -> {
            //AlertDialog 는 Builder 에 의해 생성.. Builder 를 먼저 준비하고..
            //setter 함수로 dialog 구성..
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(android.R.drawable.ic_dialog_info);
            builder.setTitle("title");
            builder.setMessage("message");
            //동일 함수로 버튼 추가가 중복되면.. 하나만 나온다..
            builder.setPositiveButton("OK", dialogHandler);
            builder.setPositiveButton("OK1", dialogHandler);
            builder.setNegativeButton("NO", dialogHandler);
            builder.setNeutralButton("MORE", dialogHandler);
            //builder.create() - AlertDialog 를 생성, show() 띄운다..
            builder.show();
        });

        String[] arrays = getResources().getStringArray(R.array.list);

        binding.button2.setOnClickListener(view -> {
            //목록 다이얼록...
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("List Dialog");
            builder.setItems(R.array.list, (dialog, whitch) -> {
                //항목 선택 이벤트.. 두번째 매개변수가 선택한 항목의 index
                Toast.makeText(Test3_1Activity.this, arrays[whitch], Toast.LENGTH_SHORT)
                        .show();
            });
            builder.show();
        });

        //항목의 초기값으로 사용.., 유저가 체크박스 상태 조정 값 저장..
        boolean[] selectedItem = new boolean[arrays.length];//사이즈만 지정하고 배열..
        //초기는 모두 false
        binding.button3.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("항목을 선택하세요");
            //체크박스가 같이 나오는 다이얼로그
            builder.setMultiChoiceItems(arrays, selectedItem, new DialogInterface.OnMultiChoiceClickListener(){
                //체크박스다.. 여러개 선택이 된다.. 하나하나의 체크박스의 체크상태를 바꿀때마다..
                //체크상태 변경만으로.. dialog 가 닫기지는 않는다..
                @Override
                public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                    selectedItem[which] = isChecked;
                }
            });
            builder.setPositiveButton("확인", (dialog, whitch) -> {
                ArrayList<String> selectedOption = new ArrayList<>();
                for(int i = 0; i < selectedItem.length; i++){
                    if(selectedItem[i]){
                        selectedOption.add(arrays[i]);
                    }
                }
                Toast.makeText(Test3_1Activity.this, selectedOption.toString(), Toast.LENGTH_SHORT).show();
            });
            builder.show();
        });

        binding.button4.setOnClickListener(view -> {
            //custom dialog 를 위한 layout xml 을 inflate 시켜서 dialog 에 지정..
            DialogTestBinding dialogTestBinding = DialogTestBinding.inflate(getLayoutInflater());
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(dialogTestBinding.getRoot());
            builder.setNegativeButton("cancel", null);
            builder.show();
        });
    }
}