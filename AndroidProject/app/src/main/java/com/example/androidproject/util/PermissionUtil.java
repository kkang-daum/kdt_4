package com.example.androidproject.util;

import android.os.Build;

import androidx.activity.ComponentActivity;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import com.example.androidproject.callback.PermissionCallback;

import java.util.HashSet;

public class PermissionUtil {
    public static void checkAllPermission(ComponentActivity activity, PermissionCallback callback){
        HashSet<String> permissionSet = new HashSet<>();

        permissionSet.add("android.permission.CALL_PHONE");

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            permissionSet.add("android.permission.READ_MEDIA_IMAGES");
        }else {
            permissionSet.add("android.permission.READ_EXTERNAL_STORAGE");
        }


        //하나의 퍼미션을 유저에게 요청하는 경우에는 RequestPermission
        //한번에 여러 퍼미션 조정을 요청한다면.. RequestMultiplePermissions
        ActivityResultLauncher<String[]> launcher = activity.registerForActivityResult(
                new ActivityResultContracts.RequestMultiplePermissions(),
                //RequestPermission 이면 result - boolean
                //RequestMultiplePermissions 의 result - Map<String, boolean> : 퍼미션 문자열
                result -> {
                    boolean allGranted = true;
                    for(Boolean isGranted: result.values()){
                        if(!isGranted){
                            allGranted = false;
                            break;
                        }
                    }
                    callback.onPermissionResult(allGranted);
                }
        );
        launcher.launch(permissionSet.toArray(new String[permissionSet.size()]));

    }
}
