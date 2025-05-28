package com.example.ch3.section2

import android.util.Log
import androidx.lifecycle.LiveData

//이 livedata 를 이용해 데이터를 발행하기는 하지만.. api 를 직접 정의하기 위해서..
//발행하기는 한다.. 약간 조작해서.
class MyLiveData: LiveData<String>() {
    fun sayHello(name: String){
        postValue("Hello $name")
    }

    override fun onActive() {
        super.onActive()
        Log.d("kkang","MyLiveData onActive")
    }

    override fun onInactive() {
        super.onInactive()
        Log.d("kkang", "MyLiveData onInactive")
    }
}