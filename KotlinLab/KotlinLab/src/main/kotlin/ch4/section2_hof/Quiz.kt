package org.example.ch4.section2_hof

class DataLoader {
    //이곳에서 데이터 로딩을 할거다..
    //성공시에 혹은 실패시에 호출할 함수만 나한테 전달하라..
    //내가 알아서 호출해 줄게..
    fun loadData(
        url: String,
        onSuccess: (List<String>) -> Unit,
        onError: (Exception) -> Unit
    ) {
        try {
            val result = mutableListOf<String>()
            result.add("hello")
            result.add("world")
            //성공상황이다..
            onSuccess(result)
        }catch(e: Exception){
            onError(e)
        }
    }
}

fun main() {
    val obj = DataLoader()
    obj.loadData(
        "http://www.naver.com",
        { data ->
            println("success")
        },
        { e ->
            println("error..")
        }
    )
}