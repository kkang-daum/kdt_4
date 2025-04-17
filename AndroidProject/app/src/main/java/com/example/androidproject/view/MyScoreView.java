package com.example.androidproject.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

import androidx.annotation.NonNull;

public class MyScoreView extends View {
    int score;
    Context context;

    MyScoreView(Context context){
        super(context);
    }
    //3개 생성자 선언....


    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        //화면 지우고...
        canvas.drawColor(Color.alpha(Color.CYAN));

        //호를 그려야 한다.. 호가 그려질 사각형을 먼저 선언..
        //360도 회색 호와, 스코어 호가 동일 사각형내에 그려짐으로 사각형을 하나 선언하고 그리면 편하다.
        RectF rectF = new RectF(10, 10, 50, 50);
        //회색으로.. 360도 원을 그리기..
        Paint paint = new Paint();//그리기 속성...
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.STROKE);//테두리만 그린다..
        paint.setAntiAlias(true);//매끄럽게..
        paint.setStrokeWidth(10);//선의 두께....
        //사각형 영역에.. paint 속성으로 호를 그린다.. 각도를 지정해서..
        canvas.drawArc(rectF, 0, 360, false, paint);

        //score 각도 계산..
        //100 -> 360
        //50 -> 180
        float endAngle = (360*score)/100;
        //score 그리기 위한 그리기 속성 지정..
        paint.setColor(Color.RED);
        canvas.drawArc(rectF, -90, endAngle, false, paint);
    }

    //액티비티에서..
    //score 를 매개변수로 지정해서.. 이 함수 호출해야...
    //<com.....MyScoreView id="@+id/scoreCustomView
    //binding.scoreCustomView.setScore(50)
    public void setScore(int score) {
        this.score = score;
        invalidate();
    }

    //만약 custom 속성으로 색상이 지정되길 원한다면..
    //values 에 attrs 에 속성 명 등록하고..
    //layout xml 파일에 등록된 속성명으로 색상 지정..
    //MyCustomView 의 생성자매개변수인 AttributeSet 을 이용해... 속성값 획득해서 이용..
}


















