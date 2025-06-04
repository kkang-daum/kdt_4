package com.example.tripapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.tripapp.ui.home.HomeScreen

//MainActivity 에 의해 출력될.. composable 을 등록.. stack 으로 관리...
@Composable
fun MainNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    navActions: MainNavigation = MainNavigation(navController)
) {
    //composable 을 stack 정보로 유지..
    NavHost(
        navController = navController,
        startDestination = "main",
        modifier = modifier
    ){
        //이곳에서 composable 로 각 화면을 등록해도 되기는 하지만..
        //화면이 많다면.. 각 composable 을 구조화 시켜서 등록시킬 수도 있다..
        navigation(startDestination = TripDestination.HOME_ROUTE, route = "main"){
            composable(TripDestination.HOME_ROUTE) {
                HomeScreen()
            }
        }
    }
}