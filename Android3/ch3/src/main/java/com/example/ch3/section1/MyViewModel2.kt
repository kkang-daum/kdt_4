package com.example.ch3.section1

import android.util.Log
import androidx.lifecycle.ViewModel

class MyViewModel2: ViewModel(){
    var count = 0

    init {
        Log.d("kkang", "MyViewModel2 create...")
    }

    //ViewModel 객체 소멸될때 호출..
    override fun onCleared() {
        super.onCleared()
        Log.d("kkang", "MyViewModel2 onCleared....")
    }
}