package com.example.ch8.section3

import com.example.ch8.section2.ItemModel

//서버에서 넘어오는 전체 데이터 추상화 클래스..
class PageListModel {
    var articles: MutableList<ItemModel>? = null
}