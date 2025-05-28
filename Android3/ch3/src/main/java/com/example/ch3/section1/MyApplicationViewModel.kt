package com.example.ch3.section1

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel

//ViewModel 생성시 매개변수로.. Application 객체를 전달하고 싶을때...
//직접 생성하지 않고.. ViewModelProvider 등으로 생성함으로..
class MyApplicationViewModel(application: Application): AndroidViewModel(application) {
    var count = 0
    init {
        Log.d("kkang", "MyApplicationViewModel create...")
    }
    fun someFun(){
        //관심의 분리이다..
        //ViewModel 이 View 는 아니다.. ViewModel 에서 Application 등을 이용해서..
        //화면 제어가 가능하다고 하더라도.. 권장할 만하지 않다..
        //application 객체를 이용할 수 있는지 테스트 때문에 사용한거다..
        Toast.makeText(getApplication(), "viewmodel toast", Toast.LENGTH_SHORT).show()
    }
}