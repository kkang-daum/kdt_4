package com.example.composelab.section4.test3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composelab.section4.test3.ui.theme.ComposeLabTheme

class Test3Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeLabTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting9(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting9(name: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        //비밀번호 입력.. textfield 를 제공한다고 가정...
        Spacer(modifier = Modifier.height(10.dp))

        //유저 입력 값이 상태로 유지가 되어야.. 입력 문자열이 화면에 출력..
        var password by remember { mutableStateOf("") }
        //상태 - 비밀번호 표시 여부 상태..
        var isVisiblePassword by remember { mutableStateOf(false) }

        //trail icon 을 제공하고자 한다.. 상태 값에 따라 다른 아이콘 제공..
        val trailIcon = @Composable {
            val icon = if(isVisiblePassword){
                Icons.Outlined.Visibility
            }else {
                Icons.Outlined.VisibilityOff
            }
            IconButton(onClick = {
                isVisiblePassword = !isVisiblePassword
            }) {
                Icon(imageVector = icon, contentDescription = "")
            }
        }
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Password") },
            visualTransformation = if(isVisiblePassword){
                VisualTransformation.None//특별한 변환 처리 하지 말고 출력하라.. 입력
                //문자열이 그대로 출력..
            }else {
                PasswordVisualTransformation()//입력 문자열이 * 로 변경되어 출력..
            },
            leadingIcon = {
                Icons.Outlined.Lock
            },
            trailingIcon = trailIcon
        )
    }
}

