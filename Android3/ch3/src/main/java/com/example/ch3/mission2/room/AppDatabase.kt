package com.example.ch3.mission2.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Search::class), version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun searchDao(): SearchDAO
}