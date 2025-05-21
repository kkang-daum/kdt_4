package com.example.ch1.section2

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.system.measureTimeMillis

suspend fun networkRequest(requestUrl: String): String {
    val url = URL(requestUrl)
    //url 로 http 연결 객체 준비..
    (url.openConnection() as? HttpURLConnection)?.run {
        requestMethod = "GET"
        setRequestProperty("Content-Type", "application/json;utf-8")
        setRequestProperty("Accept", "application/json")
        setRequestProperty("x-api-key", "reqres-free-v1")//특정 서버에서 원해서..
        val reader = BufferedReader(InputStreamReader(inputStream))
        val buffer = StringBuffer()
        reader.lines().forEach { buffer.append(it)}
        return buffer.toString()
    }
    return " "
}

fun main() = runBlocking {
    val jobs = mutableListOf<Job>()
    repeat(100){
        jobs.add(launch(Dispatchers.Default) {//time : 969
//        jobs.add(launch(Dispatchers.IO) {//time : 911
            val result = networkRequest("https://reqres.in/api/users/1")
            println(result)
        })
    }
    val time = measureTimeMillis {
        jobs.forEach { it.join() }
    }
    println("time : $time")
}