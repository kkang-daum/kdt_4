package com.example.ch8.section3

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//manifest 에 등록.. 앱 실행시 최초에 한번..
class MyApplication: Application() {
    companion object {
        val API_KEY = "3dbc3796e7a64890a9bcc16373e72592"

        val retrofitService: INetworkService

        val retrofit: Retrofit
            get() = Retrofit.Builder()
                .baseUrl("https://newsapi.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        init {
            retrofitService = retrofit.create(INetworkService::class.java)
        }
    }
}