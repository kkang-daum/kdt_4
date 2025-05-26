package com.example.ch1.section8

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.flow.stateIn
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

//MutableStateFlow 는 각 업무가 발생하는 곳에서.. 직접 MutableStateFlow 을 이용해..
//데이터가 어떻게 변경되고 발행되어야 하는지를 직접 명시하는 방법
//흔히 명령형 프로그램이라고 한다..

//test2................... stateIn.......................
//어떤 상황이 발생했을때 어떤 처리가 되어야해.. 를 stateIn 에 각종의 operator 을 연결해서..
//처리 방법만 명시.......
//선언형 프로그램....

//상황을 표현하기 위한 sealed... 클래스.. 혹은 enum
sealed class CartAction {
    data class AddItem(val item: CartItem): CartAction()
    data class RemoveItem(val itemId: Int): CartAction()
}

class ShoppingCart2(val scope: CoroutineScope){
    //flow 를 하나 만들기는 했지만.. 이 flow 는 외부에 데이터를 발행하기 위한 flow 가 아니다..
    //private
    //단지 상황이 발생했을때 그 상황을 발행하고.. 내부에서 그 상황을 구독해서..
    //선언해 놓은대로.. 코드가 쭉 실행되게 하고자....
    private val cartAction = MutableSharedFlow<CartAction>()

    //데이터를 유지할 수 있는 flow 를 선언하기는 하지만.. 어떻게 처리되어야 한다고..
    //업무처리의 흐름을 선언만 해놓겠다..
    val items: StateFlow<List<CartItem>> = cartAction
        .scan(emptyList<CartItem>()){currentItems, action -> //scan... forEach 같은 역할을 하는 operator...
            when(action){
                is CartAction.AddItem -> {
                    val existing = currentItems.find { it.id == action.item.id }
                    if(existing != null) {
                        currentItems.map { item ->
                            if(item.id == action.item.id){
                                item.copy(quantity = item.quantity + action.item.quantity)
                            }else {
                                item
                            }
                        }
                    }else {
                        currentItems + action.item
                    }
                }
                is CartAction.RemoveItem -> {
                    currentItems.filter { it.id != action.itemId }
                }
            }
        }.stateIn(
            scope = scope,
            started =  SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    //외부에서.. 데이터를 구독하기 위해서.. 사용하는 두개의 flow 를 선언..
    //flow 내부에서.. 어떤 action 이 발생했을때 어떻게 업무 처리한다고 명시만...
    val total: StateFlow<Double> = items
        .map { items -> items.sumOf { it.price * it.quantity } }
        .stateIn(
            scope = scope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0.0
        )

    fun addItem(item: CartItem){
        //MutableStateFlow 를 이용하면 실제 업무가 발생하는 곳에서..
        //Flow 를 이용해 직접 데이터를 발행하는 방식... ==>명령형..
        //stateIn().... 업무가 발생하는 곳에서 Flow 로 데이터를 직접 발행한 것이 아니라..
        //단지 어떤 상황이야만 알려주고..
        //이미 선언된 flow 의 흐름이 쭉 실행되게.. ==>선언형..
        scope.launch {
            cartAction.emit(CartAction.AddItem(item))
        }

    }
    fun removeItem(itemId: Int){
        scope.launch {
            cartAction.emit(CartAction.RemoveItem(itemId))
        }
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

    println()

    //stateIn.................................
    val cart2 = ShoppingCart2(scope)

    launch {
        cart2.items.collect { items ->
            println("cart2...${items.map { "${it.name} - ${it.quantity}" }}")
        }
    }
    launch {
        cart2.total.collect {
            println("total price... $it")
        }
    }

    delay(100)
    cart2.addItem(CartItem(1, "product1", 10.0, 2))
    delay(200)
    cart2.addItem(CartItem(2, "product2", 20.0, 1))
    delay(200)
    cart2.addItem(CartItem(1, "product1", 10.0, 1))
    delay(200)
    cart2.removeItem(2)

    delay(1000)
}