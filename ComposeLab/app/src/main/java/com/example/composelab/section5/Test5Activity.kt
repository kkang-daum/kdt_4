package com.example.composelab.section5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composelab.section5.ui.theme.ComposeLabTheme

class Test5Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeLabTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting10(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting10(name: String, modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home"){
        composable("home") { HomeScreen(navController) }
        //parameter 전달...
        composable(
            "one/{userId}/{no}",
            arguments = listOf(
                navArgument("userId"){
                    type = NavType.StringType
                },
                navArgument("no"){
                    type = NavType.IntType
                }
            )
        ) { navBackStackEntry ->
            OneScreen(
                navController,
                navBackStackEntry.arguments?.getString("userId"),
                navBackStackEntry.arguments?.getInt("no")
            )
        }
        composable("onesub") { OneSubScreen(navController)  }
        composable("two") { TwoScreen(navController)  }
    }
}

@Composable
fun HomeScreen(navController: NavController){

    //two 로 화면 이동 시킬 것이고.. two 에서 되돌아 왔을 때의 결과 데이터..
    val msg = navController.currentBackStackEntry?.savedStateHandle?.get<String>("msg")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("I am HomeScreen", fontSize = 30.sp)
        Button(onClick = { navController.navigate("one/kim/10") }) {
            Text("Go One")
        }
        Button(onClick = { navController.navigate("two")}) {
            Text("Go Two ${msg}")
        }
    }
}

@Composable
fun OneScreen(navController: NavController, userId: String?, no: Int?){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("I am OneScreen , userId = $userId, no : $no", fontSize = 30.sp)
        Button(onClick = {
            navController.navigate("onesub"){
                popUpTo("home")
            }
        }) {
            Text("Go OneSub")
        }
        Button(onClick = { navController.popBackStack() }) {
            Text("Back")
        }
    }
}

@Composable
fun OneSubScreen(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("I am OneSubScreen", fontSize = 30.sp)
        Button(onClick = { navController.popBackStack() }) {
            Text("Back")
        }
    }
}

@Composable
fun TwoScreen(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("I am TwoScreen", fontSize = 30.sp)
        //결과데이터를 포함해서.. 이전화면으로..
        Button(onClick = {
            navController.previousBackStackEntry?.savedStateHandle?.set("msg", "lee")
            navController.popBackStack()
        }) {
            Text("Back")
        }
    }
}