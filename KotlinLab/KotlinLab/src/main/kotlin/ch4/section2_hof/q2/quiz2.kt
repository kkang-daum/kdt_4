package org.example.ch4.quiz

/**
 * ch4/sec2_hof
 * */

class DataLoader{
    fun loadData(url: String, onDataLoaded: (List<String>) -> Unit, onError: (Exception) -> Unit){
        try{
            val result = listOf("hello", "world")
            onDataLoaded(result)
        }catch (e: Exception){
            onError(e)
        }
    }
}

fun main() {
    val loader = DataLoader()

    loader.loadData("https://.../api",
        onDataLoaded = { data -> println("Data loaded successfully: $data") },
        onError = { e -> println("Error loading data: ${e.message}")}
    )
}