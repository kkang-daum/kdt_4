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

fun selectTodos(context: Context): MutableList<Todo> {
    val results = mutableListOf<Todo>()
    val helper = DBHelper(context)
    val db = helper.readableDatabase
    val cursor = db.rawQuery("select * from tb_todo order by date desc", null)

    while (cursor.moveToNext()){
        cursor.run {
            val vo = Todo(getInt(0), getString(1), getString(2),
                getString(3), getInt(4))
            results.add(vo)
        }
    }
    db.close()
    return results
}

fun updateTodo(context: Context, values: ContentValues, where: String){
    val helper = DBHelper(context)
    val db = helper.writableDatabase
    db.update("tb_todo", values, where, null)
    db.close()
}
