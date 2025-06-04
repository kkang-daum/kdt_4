package com.example.tripapp.ui.myinfo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tripapp.data.myinfo.MyInfoData
import com.example.tripapp.data.myinfo.insertInfo
import com.example.tripapp.data.myinfo.selectInfo
import kotlinx.coroutines.launch

//여러 composable 에서 공유하는 데이터를 가지는 viewmodel 가정...
class MyInfoViewModel(application: Application): AndroidViewModel(application) {
    private var infoData: MyInfoData? = null
    val myInfoLiveData: MutableLiveData<MyInfoData> by lazy {
        MutableLiveData<MyInfoData>(infoData)
    }
    fun getMyInfoData(): MyInfoData? {
        return infoData
    }
    fun selectMyInfoData(){
        val myInfo = selectInfo(getApplication())
        infoData = myInfo
        myInfo?.let {
            myInfoLiveData.postValue(it)
        }
    }
    fun updateMyInfoData(myInfoData: MyInfoData): MutableLiveData<Boolean>{
        val liveData = MutableLiveData(false)
        viewModelScope.launch {
            val result = insertInfo(getApplication(), myInfoData)
            liveData.postValue(result)
        }
        return liveData
    }
}