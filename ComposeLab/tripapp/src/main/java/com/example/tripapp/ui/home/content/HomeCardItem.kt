package com.example.tripapp.ui.home.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//homescreen 의 여행상품 card 하나 추상화...
@Composable
fun HomeCardItem(
    data: HomeData,
    modifier: Modifier = Modifier
){
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color.White
        ),
        modifier = modifier
            .padding(8.dp)
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
        ) {
            Image(
                painter = painterResource(data.imageId),
                contentDescription = "",
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .padding(top = 16.dp)
            )
            Text(
                text = data.title,
                style = TextStyle(
                    fontSize = 12.sp
                ),
                modifier = Modifier
                    .padding(vertical = 4.dp, horizontal = 16.dp)
            )
            Text(
                text = data.content,
                style = TextStyle(
                    fontSize = 11.sp
                ),
                modifier = Modifier
                    .padding(vertical = 4.dp, horizontal = 16.dp)
            )
        }
    }
}