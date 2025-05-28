package com.example.ch3.mission1.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ch3.mission1.model.Item
import com.example.ch3.mission1.model.PageList
import com.example.ch3.mission1.repository.NewsRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//Activity(Fragment) 의 요청을 받아 적절한 B/L 을 실행시키고..
//발생한 데이터를 LiveData 로 발행..
class NewsViewModel: ViewModel() {
    fun getNews(query: String): MutableLiveData<List<Item>> {
        val liveData = MutableLiveData<List<Item>>()

        val repository = NewsRepository()
        repository.getNewsList(query, object : Callback<PageList> {
            override fun onResponse(call: Call<PageList>, response: Response<PageList>) {
                val results = response.body()?.articles ?: listOf<Item>()
                //데이터를 activity(fragment) 에 전달한다.. livedata 이용해서..
                liveData.postValue(results)
            }

            override fun onFailure(call: Call<PageList>, t: Throwable) {
                call.cancel()
                Log.d("kkang", "$t")
            }
        })

        return liveData
    }
}