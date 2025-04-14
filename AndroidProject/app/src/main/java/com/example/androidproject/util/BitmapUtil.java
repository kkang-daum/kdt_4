package com.example.androidproject.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.io.InputStream;

public class BitmapUtil {

    //안드로이드 이미지의 원천 타입은 Bitmap (Drawable 은 리소스 이미지를 쉽게 사용하기 위해 만든 Bitmap 추상화)
    //이 함수를 호출하면.. 이미지를 읽어서 Bitmap 으로 리턴..
    //매개변수가 이미지를 식별하는 Uri...
    //gallery app 의 목록화면에서..유저가 이미지 선택.. 되돌아 올때.. 유저 선택한 이미지의 식별자 값이 Uri

    //유저가 gallery 앱에서 사진을 선택한 순간.. 그 사진파일을 읽을 수 있는 stream 을 직접 제공...
    //그 stream 에 uri 만 지정하면.. 읽힌다.. 이게 편하다..
    //물론 파일 경로를 얻어 파일 경로로 읽을 수 있기는 하지만 stream 을 이용하는 것이 더 편하다..
    public static Bitmap getGalleryBitmapFromStream(Context context, Uri uri){
        try {
            //데이터 이미지(사진 파일 이미지, network 다운로드 이미지), 데이터 사이즈 크다..
            //그냥 로딩하면.. OOM(Out Of MemoryException) 문제 발생한다..
            //데이터 사이즈를 줄여서 로딩해야 한다..
            //화면에 출력되는 사이즈를 줄인다는 개념이 아니라..(ImageView - maxWidth)
            //로딩되는 이미지 데이터 사이즈를 줄여서.. 10M -> 100K

            //Bitmap 은 직접 생성되지 않는다.. BitmapFactory 에 의해서만 생성된다..
            //BitmapFactory 의 작업 옵션이 있고.. 이 옵션에 명시하면 알아서 줄여서 로딩..
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 10;//10분의 1로 줄여서 로딩..
            //숫자 값을 원본 이미지의 width, height 해상도 사이즈와, 화면에 출력하고자 하는 사이즈를 계산해서
            //몇분의 몇으로 줄일지를 지정...

            InputStream inputStream = context.getContentResolver().openInputStream(uri);
            //inputStream 으로 부터 넘어오는 데이터 읽어서 Bitmap 으로 만들어줘.. option 적용해서..
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, options);
            return bitmap;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //문자열로 된 파일의 경로(절대경로)를 매개변수로 지정하면 그 파일의 이미지를 읽어서 Bitmap 으로 리턴..
    //stream 으로 읽는것은 gallery app 에서 되돌아 온 순간만 가능하다.. 그 이후에는 불가능하다..
    //DetailActivity 에서 프사 변경하고.. 내일.. MainActivity에 나와야 한다..
    //db 에 유저 선택한 파일 경로를 저장했다가.. 파일 경로로 읽어야 해서..
    public static Bitmap getGalleryBitmapFromFile(Context context, String filePath){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 10;

        return BitmapFactory.decodeFile(filePath, options);
    }
}
