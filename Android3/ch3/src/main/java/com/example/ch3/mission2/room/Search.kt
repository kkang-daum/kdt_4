package com.example.ch3.mission2.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Search(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var searchTxt: String
)