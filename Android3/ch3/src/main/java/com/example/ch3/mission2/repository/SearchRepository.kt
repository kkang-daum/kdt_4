package com.example.ch3.mission2.repository

import android.util.Log
import com.example.ch3.mission2.room.AppDatabase
import com.example.ch3.mission2.room.Search

class SearchRepository(private val database: AppDatabase) {
    private val searchDao = database.searchDao()

    fun insertSearch(search: Search){
        Log.d("kkang", "222222, ${search.searchTxt}")
        searchDao.insertSearch(search)
    }
    fun getAllSearch(): List<Search>{
        return searchDao.getAll()
    }
}