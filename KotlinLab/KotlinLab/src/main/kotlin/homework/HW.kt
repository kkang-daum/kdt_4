package org.example.homework

import java.lang.reflect.Member
import javax.xml.crypto.dsig.XMLSignature.SignatureValue

enum class PaymentMethod(val processingFee: Double) {
    CREDIT_CARD(0.02){
        override fun calculateFee(amout: Double): Double {
            return amout * processingFee
        }

        override fun description(): String {
            return "신용카드 결제"
        }
    },
    BACK_TRANSFER(0.01){
        override fun calculateFee(amout: Double): Double {
            return amout * processingFee
        }

        override fun description(): String {
            return "계좌이체"
        }
    },
    POINT(0.0){
        override fun calculateFee(amout: Double): Double {
            return amout * processingFee
        }

        override fun description(): String {
            return "포인트 결제"
        }
    }
    ;
    abstract fun calculateFee(amout: Double): Double
    abstract fun description(): String
}

enum class OrderStaus {
    PENDING, PAID, CANCELLED
    ;
    fun getDescription(): String {
        return when(this){
            PENDING -> "결제 대기 중"
            PAID -> "결제 완료"
            CANCELLED -> "주문 취소"
        }
    }
}

enum class MembershipLevel(val discountRate: Double) {
    REGULAR(0.0),
    SILVER(0.05),
    GOLD(0.1),
    VIP(0.2)
    ;
    fun getDescription(): String{
        return when(this){
            REGULAR -> "일반 회원"
            SILVER -> "실버 회원 (5% 할인)"
            GOLD -> "골드 회원 (10% 할인)"
            VIP -> "VIP 회원 (20% 할인)"
        }
    }
}

data class User(
    val name: String,
    val phone: String,
    val membershipLevel: MembershipLevel = MembershipLevel.REGULAR
){
    fun getDiscountRate(): Double {
        return membershipLevel.discountRate
    }
}

data class Product(
    val name: String,
    val price: Double
)

class Order(
    val user: User,
    val product: Product,
    val paymentMethod: PaymentMethod = PaymentMethod.CREDIT_CARD,
    val status: OrderStaus = OrderStaus.PENDING
){
    fun calculateSubtotal(): Double {
        val price = product.price - (product.price * user.membershipLevel.discountRate)
        val paymentFee = paymentMethod.calculateFee(price)
        return price + paymentFee
    }
    fun printOrderDetails() {
        println("고객정보 :")
        println("   이름:${user.name} (${user.membershipLevel.getDescription()}), 연락처 : ${user.phone}")
        println("주문상품 :")
        println("   상품명:${product.name}, 단가: ${product.price}")
        println("결제정보 :")
        println("   결제방법: ${paymentMethod.description()} - 수수료 ${paymentMethod.processingFee}")
        println("   결제상태: ${status.getDescription()}")
        println("   결제금액: ${calculateSubtotal()}")

    }
}

fun main(){
    val user = User(name = "홍길동", phone = "010-1234-5678", membershipLevel = MembershipLevel.GOLD)
    val product = Product("스마트폰", 850000.0)
    val order = Order(
        user = user,
        product = product,
        paymentMethod = PaymentMethod.CREDIT_CARD,
        status = OrderStaus.PAID
    )
    order.printOrderDetails()
}