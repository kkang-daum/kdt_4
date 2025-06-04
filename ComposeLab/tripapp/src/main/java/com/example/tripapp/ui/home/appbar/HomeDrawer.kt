package com.example.tripapp.ui.home.appbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ControlledComposition
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.example.tripapp.R
import com.example.tripapp.TripDestination

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

//drawer 윗 부분.....
@Composable
private fun DrawerHeader(
    modifier: Modifier = Modifier,
    email: String?,
    photo: String?
){
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFF3890DD))
            .height(192.dp)
            .padding(start = 30.dp)
    ) {
        if(photo != null){
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(photo)
                    .build(),
                contentDescription = "",
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }else {
            Image(
                painter = painterResource(id = R.drawable.user_basic),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )
        }

        Text(
            text = "${email ?: ""}",
            style = TextStyle(
                color = Color.White,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

//ModalNavigationDrawer에의해 끌려 나오는 부분의 화면 구성..
@Composable
fun HomeDrawer(
    closeDrawer: () -> Unit,
    modifier: Modifier = Modifier,
    navigate: (String) -> Unit,
    email: String?,
    photo: String?
) {
    ModalDrawerSheet(modifier = modifier) {
        DrawerHeader(email = email, photo = photo)
        DrawerButton(
            label = "개인정보 수정",
            icon = Icons.Filled.Edit,
            modifier = Modifier.clickable {
                closeDrawer()
                navigate(TripDestination.MYINFO_ROUTE)
            }
        )
        DrawerButton(
            label = "ABOUT",
            icon = Icons.Filled.Info,
            modifier = Modifier.clickable {
                closeDrawer()
                navigate(TripDestination.ABOUT_ROUTE)
            }
        )
    }
}
