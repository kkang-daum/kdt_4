package com.example.ch3.section5

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

//db 에 저장되는 회원정보를 추상화 시킨.. Entity 클래스...
//@Entity 추가하는 것만으로.. 이 클래스의 멤버들을 저장하기 위한 테이블이 자동으로 준비된다.
//테이블 명은.. 클래스명으로..
//Entity 클래스의 멤버 변수가.. 테이블의 column
//@PrimaryKey 이 추가된 멤버의 column 이 primary key 가 된다.. 중복 허용되지 않는다..
@Entity
data class User(
    @PrimaryKey(autoGenerate = true)//자동 증가..
    var id: Int = 0,
    var lastName: String,
    var firstName: String
)

@Entity
data class Address(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var street: String,
    var state: String,
    var city: String
)
@Entity
data class User2(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var lastName: String,
    var firstName: String,
    //기초 데이터가 아닌이상.. 데이터 매핑을 명시하는 Converter 개발해서 적용해 주어야..
    var address: Address,
    var datas: List<String>,
    var regDate: Date
)

