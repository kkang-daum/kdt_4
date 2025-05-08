package com.example.ch1.section3_todo

import android.content.ContentValues
import android.content.Context

//DAO (Data Access Object) - dbms(select, insert, update 등... ) 를 위한 코드를 모아 놓은 곳..

fun insertTodo(context: Context, values: ContentValues){
    val helper = DBHelper(context)
    val db = helper.writableDatabase
    db.insert("tb_todo", null, values)
    db.close()
}