package com.example.composelab.section4.test1

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.composelab.R
import com.example.composelab.section4.test1.ui.theme.ComposeLabTheme

class Test1Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeLabTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting7(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting7(name: String, modifier: Modifier = Modifier) {
    val state = rememberScrollState()

    val context = LocalContext.current

    //세로방향 스크롤 지원하라...
    Column(modifier = modifier.then(Modifier.verticalScroll(state))) {
        Text("hello world")
        //리소스 문자열 지정..
        Text(stringResource(R.string.app_name))
        Text(
            "HelloWorld",
            color = Color.Red,
            fontSize = 20.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold
        )
        Text(
            "hello".repeat(50),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Image(
            painter = painterResource(R.drawable.flower1),
            contentDescription = "resource image"
        )
        Image(
            imageVector = Icons.Default.Home,
            contentDescription = "icon image"
        )
        Image(
            imageVector = Icons.Outlined.Home,
            contentDescription = "icon image"
        )
        AsyncImage(
            model = "https://info.ehl.edu/hubfs/Swiss-tourism-2.jpg",
            contentDescription = "network image",
            onError = {
                Log.d("kkang","network image download error...")
            }
        )
    }
}

