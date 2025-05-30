package com.example.composelab.section2.test1

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composelab.section2.test1.ui.theme.ComposeLabTheme
import kotlin.random.Random

class TestActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeLabTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
//화면 구성을 목적으로 직접 선언하는 composable... 함수이다..
@Composable
fun MainScreen(modifier: Modifier = Modifier){
    //상태가 변경되어.. 재구성 된다면.. 이 함수가 다시 호출되어.. 변경된 내용(정보)를 다시 인지한다는 의미..
    Log.d("kkang", "re-composition")

    //얼마든지 변수 호출하고.. 변수 값을 변경할 수 있지만..
    //변수값이 변경된다고.. 이 composable 이 다시 호출되지 않는다. 화면 update
    //되지 않는다.. ==> 상태 아니다.. 일반 변수이다..
    var data1  = 0
    fun changeData1(){
        Log.d("kkang", "changeData1()....")
        data1 = Random.nextInt(0, 100)
    }

    //상태 선언.. 상태를 이용하기 위한 변수명, 상태를 변경하기 위한 함수명은
    //개발자가 지정하라..
    //상태값이 변경되면.. 그 상태를 가지는 composable 이 다시 호출되어 변경
    //사항을 적용하게 된다.. ==>화면 업데이트..
    //이 composable 함수가 다시 호출이 되기는 하지만. 아래의 코드가 계속 실행되어
    //이전 변경된 상태값이 유지가 안되고.. 다시 0으로 초기화 되어.. 화면에 0만 나온다
    val (data2, setData2) = mutableStateOf(0)
    fun changeData2(){
        setData2(Random.nextInt(0, 100))
    }

    //상태값을 remember 로 감싸 주어야.. composable 이 재구성 될때..
    //다시 초기화 되지 않고.. 변경된 값을 유지하게 된다..
    var data3 by remember {
        mutableStateOf(0)
    }
    fun changeData3(){
        data3 = Random.nextInt(0, 100)
    }

    //by remeber { } 에 의해 리턴되는 것은 상태값 자체.. 참조하거나 변경하거나
    //by 를 이용하지 않고 remember { } 를 사용하면.. 리턴 되는 것은 상태가 아니라
    //상태를 가지는 MutableState 객체.. .value 로 상태 이용...
    val data4: MutableState<Int> = remember {
        mutableStateOf(0)
    }
    fun changeData4(){
        data4.value = Random.nextInt(0, 100)
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Button(onClick = { changeData1() } ) {
            Text("change data1")
        }
        Text(
            text = "data1 : $data1"
        )

        Button(onClick = { changeData2() } ) {
            Text("change data2")
        }
        Text(
            text = "data2 : $data2"
        )

        Button(onClick = { changeData3() } ) {
            Text("change data3")
        }
        Text(
            text = "data3 : $data3"
        )

        Button(onClick = { changeData4() } ) {
            Text("change data4")
        }
        Text(
            text = "data4 : ${data4.value}"
        )
    }
}

