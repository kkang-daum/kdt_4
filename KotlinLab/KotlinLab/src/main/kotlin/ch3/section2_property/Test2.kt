package org.example.ch3.section2_property

fun main() {
    //주생성자의 property 에 custom accessor 추가하고 싶다..
//    class User(var name: String)

    //주 생성자의 멤버 변수를 var, val 을 제거해서.. 해제..
    class User(name: String){
        //body 에 동일 이름의 property 를 선언해서..
        var name = name
            get() = field.uppercase()
            set(value){
                field = "Hello "+value
            }
    }

    val obj = User("kim")
    obj.name = "lee"
    println("${obj.name}")
}