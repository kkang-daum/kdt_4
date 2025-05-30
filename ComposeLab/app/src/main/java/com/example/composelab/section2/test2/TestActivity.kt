package com.example.composelab.section2.test2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composelab.section2.test2.ui.theme.ComposeLabTheme

class TestActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeLabTheme {
                //innerPadding... padding 값.. conent 를 출력할 때.. 시스템 영역을 보호가기 위해서
                //가져야 하는 padding 값을 매개변수로 알려준다.. 이 값을 활용해서 compoable 을 만들어야
                //status bar 를 보호한다..
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting2(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }

    }
}

data class User(val name: String, val email: String)

//객체 데이터를 list 에 담아서 저장할 건데.. 객체를 list 로 저장하는 규칙, 복원하는 규칙만 선언해서
//remberSavable 에 알려주면 우리 규칙대로..
val MySaver = listSaver<User, Any>(
    save = { listOf(it.name, it.email) },
    restore = {User(it[0] as String, it[1] as String)}
)

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    //상태 선언...
    //==>Activity 의 상태로 유지되지는 않는다..
    var data1 by remember {
        mutableStateOf(0)
    }
    //상태 선언..
    //Activity 의 상태로 선언..
    var data2 by rememberSaveable {
        mutableStateOf(0)
    }

    //remember 를 사용해서 개발자 임의 객체를 상태로 선언하면 activity 의 상태로 유지는 안되지만.
    //임의 객체 상태사용에 문제는 없다..
    var user1 by remember {
        mutableStateOf(User("kim", "a@a.com"))
    }

    //일반 객체를 remeberSavable 로 담으면
    //java.lang.IllegalArgumentException: MutableState containing User 에러 발생..
//    var user2 by rememberSaveable {
    //pacelable 로 준비하던가.. mapSaver, listSaver 를 이용해야 임의 객체 저장 가능..
    var user2 by rememberSaveable(stateSaver = MySaver) {
        mutableStateOf(User("kim", "a@a.com"))
    }

    //세로방향 나열..
    Column(modifier = modifier) {
        //가로방향 나열..
        Row {
            Text("remember count : ${data1}")
            Button(onClick = { data1++ }) {
                Text("increment")
            }
        }
        Row {
            Text("rememberSavable count : ${data2}")
            Button(onClick = { data2++ }) {
                Text("increment")
            }
        }
        Spacer(modifier = Modifier.height(10.dp))//여백..
        Row {
            Text("user1 : ${user1}")
            Button(onClick = { user1 = User("lee", "b@b.com") }) {
                Text("change")
            }
        }

        Spacer(modifier = Modifier.height(10.dp))//여백..
        Row {
            Text("user2 : ${user2}")
            Button(onClick = { user2 = User("lee", "b@b.com") }) {
                Text("change")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    ComposeLabTheme {
        Greeting2("Android")
    }
}