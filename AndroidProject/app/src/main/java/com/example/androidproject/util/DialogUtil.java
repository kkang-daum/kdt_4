package com.example.androidproject.util;

import android.content.Context;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

public class DialogUtil {
    //이 클래스 자체가.. 객체를 반복적으로 생성해서 각자의 메모리에 데이터를 유지하기 위한 목적이 아니고..
    //여러곳에서 사용하는 코드의 중복을 피하기 위해서 이곳에 담아놓고.. 필요한 곳에서 호출해서 쓰겠다는 의도..
    //그 함수를 object member 로 만들 필요가 있을까???? 보통의 경우 static member 로...
    public static void showToast(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
