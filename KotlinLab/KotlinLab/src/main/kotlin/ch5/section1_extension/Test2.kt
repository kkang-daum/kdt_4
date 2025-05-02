package org.example.ch5.section1_extension

open class Super {
    open fun superFun(){
        println("Super... superFun")
    }
}
class Sub: Super(){
    var data = 20
    override fun superFun() {
        println("Sub... superFun")
    }
    fun some1(data: Int){
        this.data = data
        superFun()//Sub
        super.superFun()//super... 상위를 명시해서..
    }
}

fun Sub.some2(data: Int){
    this.data = data
    superFun()
//    super.superFun()//error.. 자바 컴파일시에 Sub 로 들어가지 않는다..
    //확장 함수내에서 super 사용 불가..
}

fun Super.sayHello(){
    println("Super... sayHello")
}
fun Sub.sayHello(){
    println("Sub... sayHello")
}

fun main() {
    val obj: Super = Sub()
    obj.superFun()//다형성 ok....
    obj.sayHello()//Super... sayHello... 다형성 안된다..

}