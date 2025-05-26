package com.example.ch1.section8

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//MutableStateFlow vs stateIn()
//쇼핑카트 업무를 구현한다고 가정해보자... 물건이 추가되거나.. 제거되기도 하고..
//쇼핑카트에.. 담긴 상품목록, total price 데이터가 유지되어야 하며.. 이 데이터를 참 많은 곳에서 이용
//데이터가 계속 변경되어야 하면.. 변경된 데이터를 계속 누군가가 이용.. Channel, flow
//데이터를 수신하는 곳이 여러곳이다.. 1:N 개념으로 이용하고 싶다.. (Channel, flow)
//데이터를 한번 발행하면.. 여러곳이 동일 데이터를.. (flow)
//어디선가.. 데이터를 이용하지 않는다고 하더라도.. 쇼핑카트 데이터는 유지되어야 한다.. (hot stream)

//쇼핑카트에 담기는 상품정보 추상화..
data class CartItem(val id: Int, val name: String, val price: Double, val quantity: Int)

//case 1.........MutableStateFlow 스타일의 코드......................
class ShoppingCart1 {
    //실제 이곳에서 작업에 의한 데이터 핸들링하는 멤버는 private 으로.. 외부에 노출안시키고..
    private val _items = MutableStateFlow<List<CartItem>>(emptyList())
    val items = _items.asStateFlow()

    private val _total = MutableStateFlow(0.0)
    val total = _total.asStateFlow()

    fun addItem(item: CartItem){
        val currentItems = _items.value.toMutableList()//현재 쇼핑카트 물건 목록..
        //add 요청한 물건이 기존에 담겨진 물건인지..
        val existingIndex = currentItems.indexOfFirst { it.id == item.id }
        if(existingIndex >= 0){//이미 담긴 물건이라면..
            currentItems[existingIndex] = currentItems[existingIndex].copy(
                quantity = currentItems[existingIndex].quantity + item.quantity//수량 변경
            )
        }else{//새로운 물건..
            currentItems.add(item)
        }
        //데이터 발행해야 한다..
        _items.value = currentItems
        updateTotal()
    }
    fun removeItem(itemId: Int){
        _items.value = _items.value.filter { it.id != itemId }//발행...
        updateTotal()
    }
    private fun updateTotal(){
        _total.value = _items.value.sumOf { it.price * it.quantity }//발행...
    }

}

fun main() = runBlocking {
    val scope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    //MutableStateFlow...................................
    val cart1 = ShoppingCart1()

    launch {
        cart1.items.collect { items ->
            println("cart1...${items.map { "${it.name} - ${it.quantity}" }}")
        }
    }
    launch {
        cart1.total.collect {
            println("total price... $it")
        }
    }

    delay(100)
    cart1.addItem(CartItem(1, "product1", 10.0, 2))
    delay(200)
    cart1.addItem(CartItem(2, "product2", 20.0, 1))
    delay(200)
    cart1.addItem(CartItem(1, "product1", 10.0, 1))
    delay(200)
    cart1.removeItem(2)

    delay(1000)
}