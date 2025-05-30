package com.example.ch3.mission2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ch3.mission2.AppApplication
import com.example.ch3.mission2.repository.SearchRepository
import com.example.ch3.mission2.room.Search
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//이 ViewModel 에서는 생성자 매개변수로 application 객체를 받아야 한다..
class SearchViewModel(application: Application): AndroidViewModel(application) {
    private val appApplication = application as AppApplication
    val searchRepository: SearchRepository = SearchRepository(appApplication.db)

    //데이터 발행을 flow 로 하고 싶다..
    val searchFlow = MutableStateFlow(listOf<Search>())

    fun insertSearch(search: Search){
        //ViewModel 과 생존주기를 동일하게 가는 스코프 시용..
        viewModelScope.launch(Dispatchers.IO) {
            searchRepository.insertSearch(search)
        }
    }

    fun getAllSearch(){
        viewModelScope.launch {
            //이 위치는 main thread..

            //thread 교체해서....
            val results = withContext(Dispatchers.IO){
                searchRepository.getAllSearch()
            }

            //여기 부터는 main thread
            searchFlow.value = results//room 에서 받은 데이터를 flow 로 발행..
        }
    }

}