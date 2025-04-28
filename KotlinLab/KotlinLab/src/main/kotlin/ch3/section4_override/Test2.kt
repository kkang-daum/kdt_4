package org.example.ch3.section4_override

class Test {
    //변수 private, setter/getter - public
    var data1: String = "hello"
    //property 와 다른 접근제한자를 setter 에 지정.. custom accessor 만들고 추가하면 된다.
    var data2: String = "world"
        private set(value) { field = value }
//        private get() = field//error... get 의 접근제한자는 변경 불가하다... 항상
    //property 와 동일..
}

fun main() {
    val obj = Test()
    println("${obj.data1}, ${obj.data2}")
    obj.data1 = "aaa"
//    obj.data2 = "bbb"//error...
}