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

    var data1  = 0
    fun changeData1(){
        data1 = Random.nextInt(0, 100)
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
    }
}

