package com.example.ch3.mission2.model




data class PageList(
    var id: Long,
    var totalResults: Long,
    var articles: List<Item>
)