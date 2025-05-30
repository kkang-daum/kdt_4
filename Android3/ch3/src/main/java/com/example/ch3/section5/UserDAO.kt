package com.example.ch3.section5

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

//dbms 시 호출할 함수가 등록되는 interface

@Dao
interface UserDAO {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Insert
    fun insertUser(user: User)

    @Query("SELECT * FROM user2")
    fun getAll2(): List<User2>

    @Insert
    fun insertUser2(user: User2)
}