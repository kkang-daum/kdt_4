package com.example.androidproject.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.Window;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

import com.example.androidproject.callback.DialogCallback;
import com.example.androidproject.databinding.DialogImageBinding;

public class DialogUtil {
    //이 클래스 자체가.. 객체를 반복적으로 생성해서 각자의 메모리에 데이터를 유지하기 위한 목적이 아니고..
    //여러곳에서 사용하는 코드의 중복을 피하기 위해서 이곳에 담아놓고.. 필요한 곳에서 호출해서 쓰겠다는 의도..
    //그 함수를 object member 로 만들 필요가 있을까???? 보통의 경우 static member 로...
    public static void showToast(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    //어디선가.. custom dialog 가 떠야 하는 순간 호출...
    public static void showCustomDialog(Context context, int defaultResId){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE
        ) ;

        DialogImageBinding binding = DialogImageBinding.inflate(inflater);
        builder.setView(binding.dialogImage);

        AlertDialog dialog = builder.create();
        dialog.show();

        //dialog 의 사이즈를 뷰의 사이즈로 변경하고자 한다..
        //show 한후에 변경해야 한다..
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), defaultResId);
        Window window = dialog.getWindow();
        if(window != null){
            window.setLayout(bitmap.getWidth(), bitmap.getHeight());
        }
    }

    //메시지를 뿌리는 그리고 ok, cancel 버튼을 가진 다이얼로그를 띄우는 함수...
    //이곳에서 띄운다.. ok, cancel 버튼 클릭시 이벤트 처리는 이곳에서 못한다..
    //띄우는 곳마다 이벤트 처리가 다를 것이다..
    //이곳에서는 이벤트 발생시.. 띄우는 곳에서 지정한 callback 만 호출해 준다..
    public static void showMessageDialog(Context context, String message, String positiveText,
                                         String nagativeText, DialogCallback callback){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setTitle("Message");
        builder.setMessage(message);
        if(positiveText != null){
            builder.setPositiveButton(positiveText, (dialog, whitch) -> {
                callback.onPositiveCallback();
            });
        }
        if(nagativeText != null){
            builder.setNegativeButton(nagativeText, (dialog, which) -> {
                callback.onNegativeCallback();
            });
        }
        builder.show();
    }

}
