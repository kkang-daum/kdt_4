package org.example.ch2.section2_variable_type.one

//변수 선언과 초기화 테스트..........................
//top level - 선언과 동시에 초기화 시켜야 한다.. default 초기화 지원하지 않는다..
//==>null safety 때문에..
//경우에 따라 변수를 선언하기는 했지만.. 초기 값을 줄 수 없는 경우가 있다..
//그 경우에는 lateinit, lazy 등의 특별한 기법을 이용해야 한다..
val data1: Int = 10
var data2: Int = 20

class MyClass {
    //선언과 동시에 초기화 되어야 한다..
    val data3: Int = 10
    var data4: Int = 20

    fun someFun(){
        //local variable 에 한해서는 선언과 동시에 초기화 하지 않아도 된다.
        //선언과 초기화를 다른 라인에서 가능...
        val data5: Int
        var data6: Int

        data5 = 10
        data6 = 20

        //선언과 동시에 초기하 하지 않아도 되는 것이다.. 이용하려면 꼭 초기화 되어야 한다..
        val result = data5 + data6
    }
}