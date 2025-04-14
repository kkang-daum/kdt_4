package com.example.androidproject.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context){
        super(context, "studentdb", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table tb_student (" +
                "_id integer primary key autoincrement," +
                "name text not null," +
                "email text," +
                "phone text," +
                "photo text," +
                "memo text)");

        db.execSQL("create table tb_score (" +
                "_id integer primary key autoincrement," +
                "student_id integer not null," +
                "date," +
                "score)");
        //앱을 개발하다가.. onCreate 부분을 수정(추가, 제거, 변경)했다고 하더라도.. 반영 안된다.
        //onCreate 는 앱 인스톨 후 최초 한번만 실행됨으로.. 변경사항이 적용되지 않는다..
        //앱을 삭제하고.. 다시 실행시키던가....
        //db version 을 증가하고.. onUpgrade() 에서 변경사항 적용되게 작성..

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion == 2){
            //기존 table 삭제 후, 다시 onCreate() 호출하는 식으로 해결하겠다..
            db.execSQL("drop table tb_student");
            onCreate(db);
        }
    }
}
