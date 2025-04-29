package org.example.ch3.section8_sealed

sealed class Result {
    data class Success(val data: String): Result()
    data class Error(val message: String): Result()
}
data class Warning(val message: String): Result()
//새로운 sealed type 이 추가되면(sub 클래스), sealed type 으로 when 구문을 사용했던
//모든 곳이 에러가 난다..
//when 에서 모든 sealed 타입으로 조건을 걸게 자동으로 알려준다.. 컴파일러가..
//이게 가능한 것은 sealed 큰 컴파일러 정보이고.. 개발자가 명시적으로 봉합을 시켰기 때문에..
//컴파일러가 어떤 sealed 타입이 있는지 충분히  파악이 가능해서..
data class Loading(val message: String): Result()

//임의 위치에서.. 이중 어떤것이? when 으로 조건을 줘서 처리한다고 가정하자..
fun handleResult(result: Result) = when(result){
    is Result.Success -> println("success")
    is Result.Error -> println("error")
    is Warning -> println("warning")
    is Loading -> println("loading...")
}