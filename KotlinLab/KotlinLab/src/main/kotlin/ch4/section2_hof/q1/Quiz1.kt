package org.example.ch4.sec3_hof

//interface OnDataLoadListener {
//    fun onDataLoaded(data: List<String>)
//    fun onError(e: Exception)
//}

class DataLoader {
    fun loadData(url: String, onDataLoaded: (List<String>) -> Unit, onError: (Exception) -> Unit) {
        try {
            val result = ArrayList<String>()
            result.add("hello")  // 가상의
            result.add("world")  // 데이터를 입력 받았다
            onDataLoaded(result)  // 입력받은 데이터 load
        } catch (e: Exception) {
            onError(e)
        }
    }
}

fun main() {
    val loader = DataLoader()

    loader.loadData(
        url = "https://example.com/api",
        onDataLoaded = {
            println("Data loaded successfully: $it")
        },
        onError = {
            println("Error loading data: ${it.message}")
        }
    )
}