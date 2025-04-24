package org.example.ch2.section5_collection

fun main() {
    //arrayOf() 함수로 배열 객체 생성...
    //배열 객체를 만들면서.. 값을 줄 수 있는 경우...
    val array = arrayOf(10, "hello", true)//사이즈 3, Any 타입 => Array<Any>(3, )
    //배열 데이터 이용.. 이용순간에는 []로 가능..
    array[0] = 20
    array[2] = "world"
    println("${array[0]}, ${array[1]}")
    //Array 클래스의 객체임으로 배열의 데이터를 이용할때.. Array 의 함수를 이용해서...
    println("${array.size}, ${array.get(0)}")

    //배열 선언할 때.. 제네릭으로.. 타입을 지정해서 그 타입의 데이터만 가능하게..
    val array2 = arrayOf<Int>(10, 20, 30)//Array<Int>(3, )

    //기초 타입의 함수를 이용해서 배열을 만들어도 되고...
    val array3 = intArrayOf(10, 20)
    val array4 = doubleArrayOf(10.0, 20.0)

    //함수로 배열 변수를 선언하면서.. 사이즈만 지정하고.. 이후에 데이터 지정..
    val array5 = arrayOfNulls<Int>(3)
    array5[0] = 10

    //Array 클래스의 객체를 직접 생성해서 배열 객체를 만드는 방법................
    val array6 = Array<String>(3, {""})
    val array7 = Array<Int>(3, { 0 })

    //{ } 함수..
    val array8 = Array<Int>(3, { i -> i * 10 })//0, 10, 20
    //각 기초타입의 배열 클래스도 제공..
    val array9 = IntArray(3, { 0 })

}