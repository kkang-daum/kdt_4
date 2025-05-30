package com.example.ch3.mission2.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SearchDAO {
    @Query("SELECT * FROM search")
    fun getAll(): List<Search>

    @Insert
    fun insertSearch(search: Search)
}