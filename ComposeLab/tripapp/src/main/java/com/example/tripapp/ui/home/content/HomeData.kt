package com.example.tripapp.ui.home.content

//homescreen 에 찍히는 각 상품.. 데이터 추상화..
data class HomeData(
    val imageId: Int,
    val title: String,
    val content: String
)