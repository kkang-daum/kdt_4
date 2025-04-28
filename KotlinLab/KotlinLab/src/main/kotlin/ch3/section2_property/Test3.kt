package org.example.ch3.section2_property

fun main() {
    //프로퍼티를 선언과 동시에 초기화 시킬 수 없는 경우...
    //경우1 - nullable 로 선언하고 null 로 초기화...
    class User1 {
        var data1: String? = null
        val data2: Int? = null
    }

    //경우2 - init 에서 초기화 시키는 경우..
    //클래스 멤버에 한해서 가능하다.. 클래스 멤버는 객체 생성한 후에 이용이가능..
    //객체 생성하면 init 은 무조건 실행된다.. 즉 이용될때.. 초기화 되어 있다..
    class User2 {
        var data1: String
        val data2: Int
        init {
            data1 = "kim"
            data2 = 10
        }
    }

    //경우3 - 생성자의 body 에서 초기화 시키는 경우...
    class User3 {
        var data1: String
        val data2: Int
        constructor(){
            data1 = "kim"
            data2 = 10
        }
    }

    //경우4 - lateinit 으로 선언...
    class User4 {
        lateinit var data1: String
//        lateinit var data2: Int //error.. 기초 타입 객체에는 추가 불가..
//        lateinit var data3: String? //error.. nullable 에 사용 불가..
//        lateinit val data4: String //val 에 사용 불가..
    }
}