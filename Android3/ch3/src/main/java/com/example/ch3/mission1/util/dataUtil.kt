package com.example.ch3.mission1.util

import java.text.SimpleDateFormat

fun getDate(dateString: String?): String {

    try {
        val format1 = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'")
        val date = format1.parse(dateString)
        val sdf = SimpleDateFormat("MMM d, yyyy")
        return sdf.format(date!!)
    } catch (ex: Exception) {
        ex.printStackTrace()
        return "xx"
    }

}

fun getTime(dateString: String?): String {

    try {
        val format1 = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'")
        val date = format1.parse(dateString)
        val sdf = SimpleDateFormat("h:mm a")
        return sdf.format(date!!)
    } catch (ex: Exception) {
        ex.printStackTrace()
        return "xx"
    }

}