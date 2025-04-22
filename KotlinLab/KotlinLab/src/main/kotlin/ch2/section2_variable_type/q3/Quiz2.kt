package likelion.study.ch2.section2_variable_type.q3

class Quiz2 {
    val department = 1;

    fun calcBonus() {
        if (department == DEPARTMENT_SALE) {
            println("연봉에 10을 곱한다.")
        } else if (department == DEPARTMENT_DEV) {
            println("연봉에 50을 곱한다.")
        }
    }

    //kotlin 에서는 static 예약어가 제공되지 않는다..
    //java 의 static 으로 선언된 부분을 top level 로 선언하던가..
    //특정 클래스내에 담고. 그 클래스명.멤버명으로 이용되게 할려면..
    //companion object {}
    companion object {
        const val DEPARTMENT_SALE = 0
        const val DEPARTMENT_DEV = 1
        const val DEPARTMENT_MARKETING = 2
    }
}

fun main() {
    val data1 = 10
    val data2 = data1.toDouble()
    val data3 = data2.toInt()

    println("data1: $data1, data2: $data2, data3: $data3")

    val str = "10"
    val intStr = str.toInt()
    val str2 = (20).toString()
    println("int Str: $intStr, str2: $str2")
    println("${str + 10}, ${intStr + 10}")
}