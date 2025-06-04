package com.example.tripapp.data.myinfo

import android.content.Context
import android.database.Cursor
import com.example.tripapp.data.DBHelper

fun insertInfo(context: Context, info: MyInfoData): Boolean {
    try {
        val db = DBHelper(context).writableDatabase
        db.execSQL(
            "insert into TB_INFO (email, phone, photo) values (?, ?, ?)",
            arrayOf<String?>(info.email, info.phone, info.photo)
        )
        db.close()
        return true
    }catch (e: Exception){
        e.printStackTrace()
        return false
    }
}

fun selectInfo(context: Context): MyInfoData? {
    try {
        val db = DBHelper(context).readableDatabase
        val cursor = db.rawQuery("select * from TB_INFO order by _id DESC limit 1", null)
        val myInfo = if(cursor.moveToFirst()){
            MyInfoData(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3))
        }else {
            null
        }
        return myInfo
    }catch (e: Exception){
        e.printStackTrace()
        return null
    }
}