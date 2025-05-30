package com.example.composelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composelab.ui.theme.ComposeLabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //실제 화면 출력하겠다는 것이 아니라.. setContentView() 가 아니라..
        //화면 구성 정보를 설정하겠다..
        setContent {
            //테마.. ui.theme 패키지에 선언된 테마..
            ComposeLabTheme {
                //화면의 기본 구조 설정해주는 composable(화면의 구성요소)
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    //개발자가 만든 composable
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    //문자열 출력정보를 가지는 composable
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

//개발자 tool... 미리보기 지원을 위해서..
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeLabTheme {
        Greeting("Android")
    }
}