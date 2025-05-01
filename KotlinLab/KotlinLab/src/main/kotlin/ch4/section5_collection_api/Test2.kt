package org.example.ch4.section5_collection_api

import com.google.gson.Gson
import java.io.File

data class Data(val id: Int, val name: String, val addr: String, val age: String)

fun main() {
    //file 에서 읽기...
    val file = File("build/resources/main/test.json")
    val inputStream = file.inputStream()
    //json 에서 읽은 전체 문자열..
    val inputString = inputStream.bufferedReader().use { it.readText() }

    //gson 으로 json 문자열을 파싱해서.. 우리가 원하는 개체 타입으로 변형..
    val gson = Gson()
    val list: List<Data> = gson.fromJson(inputString, Array<Data>::class.java).toList()

    //seoul 이 포함된 데이터중 age 로 grouping.. 해서 출력..
    list.filter { it.addr.equals("seoul") }
        .groupBy { it.age }
        .forEach {
            println("${it.key} - ${it.value.count()}명")
            it.value.forEach {
                println("${it.name} ${it.addr} ${it.age}")
            }
        }

}