package com.example.composelab.section3.test3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composelab.section3.test3.ui.theme.ComposeLabTheme

class Test3Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeLabTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting5(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting5(name: String, modifier: Modifier = Modifier) {
    //화면에 나오는 composable 을 조정.. 화면 update... 상태 유지해야 한다..
    var aVisible by remember {
        mutableStateOf(listOf(true, false))
    }
    Column(modifier = modifier) {
        Box(modifier = Modifier.weight(1f).fillMaxWidth()){
            androidx.compose.animation.AnimatedVisibility(
                visible = aVisible.get(0),//나오는 상황..
                enter = fadeIn(),
                exit = fadeOut(),
                modifier = Modifier.align(Alignment.Center)
            ) {
                MyText(data = "A")
            }

            androidx.compose.animation.AnimatedVisibility(
                visible = aVisible.get(1),//나오는 상황..
                enter = fadeIn(),
                exit = fadeOut(),
                modifier = Modifier.align(Alignment.Center)
            ) {
                MyText(data = "B")
            }
        }

        Row {
            Button(modifier = Modifier.weight(1f), onClick = {
                aVisible = listOf(true, false)
            }) {
                Text("A")
            }
            Button(modifier = Modifier.weight(1f), onClick = {
                aVisible = listOf(false, true)
            }) {
                Text("B")
            }
        }
    }
}

@Composable
fun MyText(data: String, modifier: Modifier = Modifier){
    Text(
        data,
        fontSize = 150.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .border(width = 4.dp, color = Color.Black)
            .padding(10.dp)
            .then(modifier)
    )
}