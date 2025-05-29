package com.example.ch3.section3

import android.os.SystemClock
import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

//LiveData - owner 에게 데이터 발행해야 하는 경우..
class MyActivityLifecycleObserver: LiveData<Int>(), DefaultLifecycleObserver {

    //지속적으로 진행되어야 하는 업무를 가정..
    var isRunning = false
    var count = 0
    inner class ThreadClass: Thread(){
        override fun run() {
            while (isRunning){
                SystemClock.sleep(100)
                postValue(count++)//데이터 발행..
            }
        }
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        Log.d("kkang", "MyActivityLifecycleObserver... onStart")
        isRunning = true
        ThreadClass().start()
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        Log.d("kkang", "MyActivityLifecycleObserver... onStop")
        isRunning = false
    }
}