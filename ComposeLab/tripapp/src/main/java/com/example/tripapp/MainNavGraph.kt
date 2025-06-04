package com.example.tripapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController

//MainActivity 에 의해 출력될.. composable 을 등록.. stack 으로 관리...
@Composable
fun MainNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
)