package com.example.ch3.section3

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

//앱 전체의 라이프사이클 감지..
class MyProcessLifecycleObserver: DefaultLifecycleObserver {
    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        Log.d("kkang", "MyProcessLifecycleObserver... onStart")
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        Log.d("kkang", "MyProcessLifecycleObserver... onStop")
    }
}