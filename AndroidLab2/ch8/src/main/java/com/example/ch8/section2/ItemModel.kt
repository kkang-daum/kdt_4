package com.example.ch8.section2

//서버에서 넘어오는 신문기사 하나를 추상화시킨.. 클래스..
//가급적 서버에서 넘어오는 json 데이터를 분석해서.. 각 데이터의 key 이름으로.. 변수명을..
//DTO
class ItemModel(
    var id: Long = 0,
    var author: String? = null,
    var title: String? = null,
    var description: String? = null,
    var urlToImage: String? = null,
    var publishedAt: String? = null
)