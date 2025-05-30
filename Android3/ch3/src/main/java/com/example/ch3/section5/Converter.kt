package com.example.ch3.section5

import androidx.room.TypeConverter
import com.google.gson.Gson
import java.util.Date

//Entity 의 기초타입이 아닌 데이터의 매핑 방법을 명시..
//이 클래스를 AppDatabase 에 등록, Room 에서 개발자가 만든 매핑 규칙을 이용할 수 있게..
class Converter {
    //annotation....
    //함수명은 의미가 없다.. 타입 문제임으로.. 매개변수 타입과 리턴타입을 보고 어느 converter
    //를 사용할지 결정..
    //List<String> 형태의 데이터(프로그램적 데이터)를 String(json) 으로 변형..
    //room 내부에서.. 데이터를 json 으로 만들어 분석해서.. db 에 저장하거나 획득..
    @TypeConverter
    fun fromListToString(value: List<String>?): String {
        return Gson().toJson(value)
    }
    //위는 : insert
    //아래는 : select
    @TypeConverter
    fun fromStringToList(value: String): List<String>{
        return Gson().fromJson(value, Array<String>::class.java).toList()
    }

    @TypeConverter
    fun fromTimeToDate(value: Long?): Date? {
        return value?.let { Date(it) }
    }
    @TypeConverter
    fun fromDateToTime(date: Date?): Long? {
        return date?.time?.toLong()
    }

    @TypeConverter
    fun fromStringToAddress(value: String?): Address {
        return Gson().fromJson(value, Address::class.java)
    }
    @TypeConverter
    fun fromAddressToString(value: Address): String {
        return Gson().toJson(value)
    }
}