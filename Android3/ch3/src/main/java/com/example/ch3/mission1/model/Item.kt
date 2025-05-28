package com.example.ch3.mission1.model


data class Item(
    var id: Long,
    var author: String?,
    var title: String,
    var description: String,
    var urlToImage: String,
    var url: String,
    var publishedAt: String)