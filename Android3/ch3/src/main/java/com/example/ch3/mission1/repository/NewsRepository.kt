package com.example.ch3.mission1.repository

import com.example.ch3.mission1.AppApplication
import com.example.ch3.mission1.model.PageList
import com.example.ch3.mission1.retrofit.RetrofitService
import retrofit2.Callback

//누군가의 요청을 받아서.. 네트워킹을 통해 데이터를 구해오는 역할..
class NewsRepository {
    //query - 검색어..
    fun getNewsList(query: String, callback: Callback<PageList>){
        val apiService = AppApplication.apiService()
        apiService.getNewsList(
            q = query,
            apiKey = RetrofitService.API_KEY,
            page = 1,
            pageSize = 10
        ).enqueue(callback)
    }
}