package com.example.ch3.mission2

import android.app.Application
import androidx.room.Room
import com.example.ch3.mission2.retrofit.RetrofitService
import com.example.ch3.mission2.room.AppDatabase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppApplication: Application() {
    companion object{
        fun apiService(): RetrofitService =
            Retrofit.Builder()
                .baseUrl("https://newsapi.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitService::class.java)
    }

    lateinit var db: AppDatabase
    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "test"
        ).allowMainThreadQueries()
            .build()
    }
}