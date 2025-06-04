package com.example.tripapp.ui.home.appbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

//HomeDrawer 의 메뉴 하나를 구성..
@Composable
private fun DrawerButton(
    label: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
){
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth().padding(16.dp)
    ) {
        Icon(icon, contentDescription = "")
        Spacer(Modifier.width(16.dp))
        Text(label, style = MaterialTheme.typography.bodySmall)
    }
}

//ModalNavigationDrawer에의해 끌려 나오는 부분의 화면 구성..
@Composable
fun HomeDrawer(

) {
}