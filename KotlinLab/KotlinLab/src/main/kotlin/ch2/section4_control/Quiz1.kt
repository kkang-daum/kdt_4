package org.example.ch2.section4_control

class Quiz1 {
//    fun calcBonus(salary: Int, bonusRate: Double): String{
//        //step1.................
////        if(salary <= 0 || bonusRate <= 0){
////            return "잘못된 데이터를 입력했습니다."
////        }else {
////            val bonus = (salary * bonusRate).toInt()
////            return "지급받을 보너스는 $bonus 원 입니다."
////        }
//
//
//        //step2.................
////        val result = if(salary <= 0 || bonusRate <= 0){
////            "잘못된 데이터를 입력했습니다."
////        }else {
////            val bonus = (salary * bonusRate).toInt()
////            "지급받을 보너스는 $bonus 원 입니다."
////        }
////        return result
//
//        //step3......................
////        return if(salary <= 0 || bonusRate <= 0){
////            "잘못된 데이터를 입력했습니다."
////        }else {
////            val bonus = (salary * bonusRate).toInt()
////            "지급받을 보너스는 $bonus 원 입니다."
////        }
//    }

    fun calcBonus(salary: Int, bonusRate: Double) = if(salary <= 0 || bonusRate <= 0){
        "잘못된 데이터를 입력했습니다."
    }else {
        val bonus = (salary * bonusRate).toInt()
        "지급받을 보너스는 $bonus 원 입니다."
    }
}

fun main() {
    val obj = Quiz1()
    println(obj.calcBonus(1000, 0.25))
}