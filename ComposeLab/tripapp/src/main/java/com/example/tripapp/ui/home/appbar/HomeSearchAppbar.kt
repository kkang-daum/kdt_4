package com.example.tripapp.ui.home.appbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun HomeSearchAppbar(
    onSearch: (String) -> Unit,//검색시에 호출할 함수를 전달하라..
    closeSearchbar: () -> Unit,//검색바를 닫기 위한 함수를 전달하라..
    modifier: Modifier = Modifier
) {
    //유저 입력 검색어..
    var searchQuery by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }//포커스 요청자..
    val focusManager = LocalFocusManager.current//포커스 제어자..

    TextField(
        value = searchQuery,
        onValueChange = {
            searchQuery = it
        },
        modifier = modifier.fillMaxWidth()
            .focusRequester(focusRequester).then(Modifier.padding(16.dp)),
        placeholder = { Text("검색어를 입력하세요") },
        singleLine = true,
        leadingIcon = @Composable {
            IconButton(onClick = {
                closeSearchbar()
                searchQuery = ""
                focusManager.clearFocus()
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = ""
                )
            }
        },
        trailingIcon = {
            //X 아이콘을 제공해서.. 입력 문자열 한꺼번에 제거...
            //한자라도 입력해야 나오게...
            if(searchQuery.isNotEmpty()){
                IconButton(onClick = {
                    searchQuery = ""
                }) {
                    Icon(imageVector = Icons.Default.Clear, contentDescription = "")
                }
            }
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            //키보드의 검색 버튼을 누른 순간의 이벤트..
            onSearch = {
                onSearch(searchQuery)
                focusManager.clearFocus()
                closeSearchbar()
            }
        )
    )
}