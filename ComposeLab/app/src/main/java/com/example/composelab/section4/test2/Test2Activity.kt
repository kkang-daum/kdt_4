package com.example.composelab.section4.test2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.collection.emptyLongSet
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composelab.section4.test2.ui.theme.ComposeLabTheme

class Test2Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeLabTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting8(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting8(name: String, modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()
    Column(modifier = modifier.then(Modifier.verticalScroll(scrollState))) {
        //checkbox..........................
        //모든 유저 입력요소.. (checkbox, switch, slider.. textfield...)
        //유저 입력 값이.. 화면에 변경되려면.. 상태 선언되어야..
        var checkState by remember {
            mutableStateOf(true)
        }
        Checkbox(
            checked = checkState,
            onCheckedChange = {
                checkState = it
            }
        )

        //selectedGroup...
        val arrayDatas = arrayOf("data1", "data2", "data3")
        //선택 상황을 유지.. 화면 re-composition
        val (selected, onChangeSelected) = remember { mutableStateOf(arrayDatas[0]) }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .selectableGroup()
                .padding(10.dp)
        ) {
            arrayDatas.forEach {
                Card(
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                    modifier = Modifier
                        .weight(1f)
                        .selectable(
                            selected = (it == selected),
                            onClick = { onChangeSelected(it) }
                        )
                ) {
                    if(selected == it)
                        Text(text = "$it", color = Color.Red)
                    else
                        Text(text = "$it", color = Color.Black)
                }
            }
        }
    }
}

