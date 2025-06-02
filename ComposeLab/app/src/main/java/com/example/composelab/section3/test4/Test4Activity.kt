package com.example.composelab.section3.test4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.example.composelab.section3.test4.ui.theme.ComposeLabTheme

class Test4Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeLabTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting6(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

//화면을 구성하기 위한 함수가 아니라..composable 에 선언될 제약조건만 따로 등록하기 위한 함수..
private fun myConstraintSet(): ConstraintSet {
    return ConstraintSet {
        //button2 - 나중에 어떤 composable 에서 식별자로 지정하는 문자열..
        //button - 이 ConstraintSet 내에서만 사용되는 변수..
        val button = createRefFor("button2")
        val text = createRefFor("text2")

        constrain(button) {
            top.linkTo(parent.top, margin = 10.dp)
            start.linkTo(parent.start, margin = 10.dp)
        }
        constrain(text) {
            top.linkTo(button.bottom, margin = 10.dp)
            start.linkTo(button.end, margin = 10.dp)
        }

    }
}

@Composable
fun Greeting6(name: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        //참조정보 설정..
//        ConstraintLayout(
//            modifier = Modifier
//                .background(Color.Yellow)
//        ) {
//            //참조 정보 설정..
//            val (text1, button1) = createRefs()
//            //composable 에 선언된 참조정보를 지정해서 그 참조정보로 식별되게..
//            Button(onClick = { }, modifier = Modifier.constrainAs(button1){
//                top.linkTo(parent.top, margin = 10.dp)
//                start.linkTo(parent.start, margin = 10.dp)
//            }) {
//                Text("Button1")
//            }
//            Text("Hello", modifier = Modifier.constrainAs(text1){
//                top.linkTo(button1.bottom, margin = 10.dp)
//                start.linkTo(button1.end, margin = 10.dp)
//            })
//        }


        //ConstraintSet...............................
        val constraintSet = myConstraintSet()
        ConstraintLayout(
            constraintSet,
            modifier = Modifier.background(Color.Yellow)
        ) {
            //layoutId 를 이용해서 ConstraintSet 에 선언한 참조 문자열 지정..
            Button(onClick = {}, modifier = Modifier.layoutId("button2")) {
                Text("Button")
            }
            Text("hello", modifier = Modifier.layoutId("text2"))
        }
    }
}

