package com.example.tripapp.ui.home.appbar


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAppBar(
    //화면에 메뉴(flow menu) 출력 상태..
    showMenu: MutableState<Boolean> = remember { mutableStateOf(false) },
    openDrawer: () -> Unit,
    showSearchbar: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text("Trip App")
        },
        navigationIcon = {
            IconButton(
                onClick = { openDrawer() }
            ) {
                Icon(Icons.Filled.Menu, contentDescription = "")
            }
        },
        actions = {
            IconButton(onClick = {
                showSearchbar()
            }) {
                Icon(Icons.Filled.Search, contentDescription = "")
            }
            IconButton(onClick = {
                showMenu.value = !showMenu.value
            }) {
                Icon(Icons.Filled.MoreVert, contentDescription = "")
            }
            DropdownMenu(
                expanded = showMenu.value,
                onDismissRequest = { showMenu.value = false }
            ) {
                DropdownMenuItem(
                    text = { Text("설정", style = MaterialTheme.typography.bodySmall)},
                    onClick = { }
                )
            }
        }
    )
}