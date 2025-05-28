package com.example.ch3.section2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {

    private var count = 0

    //데이터의 종류가 다르다면 몇개의 livedata 준비 가능..
    val liveData = MutableLiveData<Int>()

    fun changeCount(){
        count++
        //누가 livedata 를 observe 하고 있을 지는 모르겠지만.. observe 하는 곳에
        //데이터 전달..
        liveData.postValue(count)
    }
}