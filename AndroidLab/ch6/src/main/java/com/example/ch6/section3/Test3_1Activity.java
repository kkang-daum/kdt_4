package com.example.ch6.section3;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ch6.R;
import com.example.ch6.databinding.ActivityTest31Binding;

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

        String data = "복수초 \n img \n 이른봄 설산에서 만나는 복수초는 어쩌구...";

        //문자열 데이터 추가
        //필요한 만큼 각 Span 객체를 추가..
        SpannableStringBuilder builder = new SpannableStringBuilder(data);

        int start = data.indexOf("img");//img 라는 문자열이 있는 최초의 위치..
        if(start > -1){//없으면 -1, 0이상이라면 무조건 있다는 의미..
            int end = start + "img".length();

            //이미지 준비..
            //문자열 데이터 - String
            //이미지 데이터 - Drawable/Bitmap
            Drawable dr = ResourcesCompat.getDrawable(getResources(), R.drawable.img1, null);
            //그리는 좌표 설정..
            dr.setBounds(0, 0, dr.getIntrinsicWidth(), dr.getIntrinsicWidth());
            ImageSpan span = new ImageSpan(dr);
            builder.setSpan(span, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        start = data.indexOf("복수초");
        if(start > -1){
            int end = start + "복수초".length();
            StyleSpan styleSpan = new StyleSpan(Typeface.BOLD);
            RelativeSizeSpan sizeSpan = new RelativeSizeSpan(2.0f);
            builder.setSpan(styleSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.setSpan(sizeSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        binding.text1.setText(builder);


        //html 태그 효과를 적용해서.. 문자열 출력..
        //브라우저로 출력하는 것 아니다.. TextView 에...
        //내부적으로는 Span 클래스 이용한다.. 단지 우리가 안하는 것 뿐이다..
        String html = "<font color='RED'>얼레지</font><br/><img src='img1'/><br/>곰배령...";

        //만약 html 내에 <img> 만 없다면.. 매개변수 하나짜리..
        //<img> 태그가 있다면 그 위치에 이미지가 출력되어야 한다.. 이미지 획득해야 한다.. 그걸 해주지는 않는다.
        //브라우저 아니다..
        //<img> 태그가 있다면 그 위치에 출력될 이미지는 우리 코드로 직접 획득해야 한다..
        //MyImageGetter 가 그 목적의 클래스..
        binding.text2.setText(
                Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY, new MyImageGetter(), null));
    }

    class MyImageGetter implements Html.ImageGetter{
        //fromHtml() 함수가 분석하는 문자열에 <img> 를 만나면 호출.. 10개면 10번 호출..
        //매개변수로 img 태그의 src 값을 전달해 준다..
        @Override
        public Drawable getDrawable(String source) {
            //서버, 파일, 리소스등 직접 획득해서 리턴.. 리턴 시킨 이미지가 <img> 위치에 출력..
            if(source.equals("img1")){
                Drawable dr = ResourcesCompat.getDrawable(getResources(), R.drawable.img2, null);
                dr.setBounds(0, 0, dr.getIntrinsicWidth(), dr.getIntrinsicHeight());
                return dr;
            }
            //이미지 없다..는 의미..
            return null;
        }
    }
}