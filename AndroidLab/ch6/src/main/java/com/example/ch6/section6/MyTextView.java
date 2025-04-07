package com.example.ch6.section6;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

//주가를 출력하는 TextView 개발..
//주가 데이터만 지정되면.. 알아서.. 빨강, 파랑으로 출력할지 스스로 결정..
//우리가 만드는 뷰도 최종 능력은 문자열 출력이다.. 칼라 결정 능력만 추가되기를 원한다..
//TextView 직접 상속 안되고.. 동일한 능력의 AppCompatTextView 상속..
public class MyTextView extends AppCompatTextView {
    //만들어진 뷰를 activity 개발자가 layout xml 파일에 등록해서 사용한다면..
    //inflate 시에 상황에 따라(xml 설정) 호출되는 생성자가 다르다..
    //가급적 3개의 생성자를 모두 준비해 주는 것이 권장사항이다.
    public MyTextView(Context context){ super(context); }
    public MyTextView(Context context, AttributeSet attrs){ super(context, attrs); }
    public MyTextView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
    }
    //뷰의 화면을 드로잉 하기 위해서 자동 호출...
    @Override
    protected void onDraw(Canvas canvas) {
        try {
            //뷰의 지정된 문자열 획득, 주가라고 가정해서 숫자로 변형..
            int value = Integer.parseInt(getText().toString());
            if(value > 0){
                setTextColor(Color.RED);
            }else if(value < 0){
                setTextColor(Color.BLUE);
            }
            //최종 문자열이 그려지기 전에.. 색상만 원하는데로 조정하고..
            //그리는 것은 상위 클래스에 잘 구현되어 있을 것이다.. 상위의 함수를 호출해서 그리게..
            super.onDraw(canvas);
        }catch (Exception e){
            super.onDraw(canvas);
        }
    }
}
