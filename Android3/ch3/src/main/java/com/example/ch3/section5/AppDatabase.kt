package com.example.ch3.section5

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

//Entity, Dao 등은 필요에 의해 여러개 선언...
//Database 클래스 선언은 한번만..
@Database(entities = arrayOf(User::class, User2::class, Address::class), version = 1)
@TypeConverters(Converter::class)
abstract class AppDatabase: RoomDatabase() {
    //dao 를 획득할 함수..
    abstract fun userDao(): UserDAO
}