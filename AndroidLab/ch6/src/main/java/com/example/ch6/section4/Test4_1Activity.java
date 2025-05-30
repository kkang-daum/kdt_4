package com.example.ch6.section4;

import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ch6.R;
import com.example.ch6.databinding.ActivityTest41Binding;

public class Test4_1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        ActivityTest41Binding binding = ActivityTest41Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //WebView(내장 브라우저) 다양한 설정...
        WebSettings settings = binding.webView.getSettings();
        //WebView 내의 js engine 이 기본 꺼져있다. 이렇게 되면 html 내의 js 가 실행되지 않는다..
        //js engine 구동되게 할려고..
        settings.setJavaScriptEnabled(true);

        //remote, local 모두 가능... remote 할려면. AndroidManifest.xml 에 퍼미션 선언되어야..
        binding.webView.loadUrl("file:///android_asset/test.html");

        //java(native) 에서... js 의 함수 호출...
        binding.lineBtn.setOnClickListener(view -> {
            binding.webView.loadUrl("javascript:lineChart()");
        });
        binding.barBtn.setOnClickListener(view -> {
            binding.webView.loadUrl("javascript:barChart()");
        });

        //js -> java 의 함수 호출....
        //java 의 데이터 획득.. , js 에서 할수 없는 일을 java 가 대신 구현해 놓고.. 그 작업이 이루어지게..
        //java 에서 객체를 공개해야 하고.. 공개된 객체의 함수만 호출이 가능하다..
        //공개한 객체를 "android" 라는 객체명으로 js 에서 사용하라.. js 객체명은 개발자 임의 이름..
        //객체명을 바꾸어서 여러 객체 공개 가능..
        binding.webView.addJavascriptInterface(new JavascriptTest(), "android");

        //이벤트 등록...
        binding.webView.setWebViewClient(new MyWebClient());
        binding.webView.setWebChromeClient(new MyWebChrome());
    }

    class JavascriptTest {
        //이 클래스의 객체가 js 에 공개된다고 하더라도.. js 에서 호출할 수 있는 함수는 아래의 어노테이션이
        //추가된 함수만..
        //이 객체내에 함수가 여러개 있다면.. 개발자가 어떤 함수는 호출이 되고, 어떤 함수는 호출이 안되고..
        //함수를 또 구분해야 해서..
        @JavascriptInterface
        public String getChartData(){
            StringBuffer buffer = new StringBuffer();
            buffer.append("[");

            for(int i = 0; i < 14; i++){
                buffer.append("["+i + ","+Math.sin(i)+"]");
                if(i<13)
                    buffer.append(",");
            }

            buffer.append("]");
            return buffer.toString();
        }
    }

    //WebView 에서의 유저 이벤트 핸들러....
    //일반적인 이벤트 핸들러는 보통 인터페이스 구현(OnClickListener 처럼)이지만.. 함수가 너무 많다..
    //인터페이스 구현이면 쓰지도 않을 함수를 오버라이드 받아야 한다.. 그래서 클래스로 만들어 놓고..
    //필요한 것만 오버라이드 받으라는 의도이다..
    class MyWebClient extends WebViewClient {
        //webview 내에서 유저가 링크 클릭해서.. 새로운 html 을 띄우려고 하는 순간의 이벤트..
        //매개변수 정보로 어느 url 로 로딩할려고 하는지 알아 낼 수 있고..
        //못가게 하거나.. 원하는 데이터를 추가해서 로딩하거나..
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            Toast.makeText(Test4_1Activity.this, request.getUrl().toString(),
                    Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    //브라우저 자체 이벤트.. 핸들러.. html 로딩 상태, alert 등이 뜨는 순간등...
    class MyWebChrome extends WebChromeClient {
        //js alert() 의 메시지가 매개변수로 전달..
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            //js alert -> java dialog 스타일로 변경해서 띄운다..
            Toast.makeText(Test4_1Activity.this, message, Toast.LENGTH_SHORT).show();
            //코드적으로 alert 창의 확인 버튼을 누르게 해서.. 화면에 안보이게 한다..
            result.confirm();
            return true;
        }
    }
}