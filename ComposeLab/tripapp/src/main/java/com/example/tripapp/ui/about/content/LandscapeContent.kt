package com.example.tripapp.ui.about.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tripapp.R

@Composable
fun LandscapeContent(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.about_main),
            contentDescription = "",
            modifier = Modifier
                .width(width = 200.dp)
                .padding(start = 20.dp),
            contentScale = ContentScale.FillWidth
        )

        Column(
            modifier = 
        ) {

        }
    }
}