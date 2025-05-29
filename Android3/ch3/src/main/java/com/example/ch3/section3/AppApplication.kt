package com.example.ch3.section3

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner

class AppApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        //앱의 라이프사이클 옵저버 등록..
        ProcessLifecycleOwner.get().lifecycle.addObserver(MyProcessLifecycleObserver())
    }

}