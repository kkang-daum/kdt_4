package com.example.tripapp.ui.home.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tripapp.R

//data... 서버에서 받은 데이터로 가정...
val dataList = listOf(
    HomeData(R.drawable.main_swiss, "스위스", "최대 20% 할인"),
    HomeData(R.drawable.main_australia, "호주", "최대 10% 할인"),
    HomeData(R.drawable.main_georgia, "조지아", "최대 20% 할인"),
    HomeData(R.drawable.main_mongolia, "몽골", "최대 20% 할인"),
    HomeData(R.drawable.main_nepal, "네팔", "최대 20% 할인"),
    HomeData(R.drawable.main_hawaii, "하와이", "최대 20% 할인")
)

//HomeScreen 의 본문 내용.....
@Composable
fun HomeContent(
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        item {
            Box {
                Image(
                    painter = painterResource(R.drawable.main_bg_1),
                    contentDescription = "",
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
                Text(
                    text = "연말연시 특별 할인 이벤트 \n 최대 20% 할인",
                    style = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 50.dp)
                )
            }
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("특가 여행지")
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF3899DD)
                    )
                ) {
                    Row {
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = ""
                        )
                        Text("전체 여행지 보기")
                    }
                }
            }
        }
        //항목의 갯수를 먼저 계산..
        //한 항목에 두개의 card 가 나오게 처리..
        val rowCount =
            if(dataList.size % 2 == 0) dataList.size / 2
            else dataList.size /2 +1
        items(rowCount){
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                HomeCardItem(
                    dataList.get(it * 2),
                    modifier = Modifier.weight(1f)
                )
                HomeCardItem(
                    dataList.get(it * 2 + 1),
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}