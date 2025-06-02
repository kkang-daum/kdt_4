package com.example.composelab.section3.test2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composelab.section3.test2.ui.theme.ComposeLabTheme

class Test2Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeLabTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting4(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting4(name: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        //align 을 지정하지 않은 상태.............
//        Row(modifier = Modifier
//            .background(Color.Yellow)
//            .fillMaxWidth()
//            .height(300.dp)
//        ) {
//           MyText(data = "A")
//           MyText(data = "B")
//           MyText(data = "C")
//        }

        //Arrangement(주축), Alignment(반대축)
        Row(modifier = Modifier
            .background(Color.Yellow)
            .fillMaxWidth()
            .height(300.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            MyText(data = "A")
            MyText(data = "B")
            MyText(data = "C")
        }
    }
}

@Composable
fun MyText(data: String, modifier: Modifier = Modifier){
    Text(
        data,
        fontSize = 50.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .border(width = 4.dp, color = Color.Black)
            .padding(10.dp)
            .then(modifier)
    )
}