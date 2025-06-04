package com.example.tripapp.ui.home

import android.widget.Toast
import androidx.collection.mutableObjectIntMapOf
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.navOptions
import com.example.tripapp.ui.home.appbar.HomeAppBar
import com.example.tripapp.ui.home.appbar.HomeDrawer
import com.example.tripapp.ui.home.appbar.HomeSearchAppbar
import com.example.tripapp.ui.home.content.HomeContent
import com.example.tripapp.ui.myinfo.MyInfoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    //drawer 의 close 함수가.. suspend 함수로 설계되어 있어서..
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navigate: (String) -> Unit,
    viewModel: MyInfoViewModel
){
    val context = LocalContext.current
    var isSearchActive by remember { mutableStateOf(false) }

    //LiveData 를 컴포즈의 state 로 변환 ...
    //LiveData 가 변경된다고 하더라도.. LiveData 가 composable 의 상태가 아님으로..
    //변경되었을때.. 화면이 갱신되지는 않아서..
    var infoState = viewModel.myInfoLiveData.observeAsState(null)
    LaunchedEffect(true) {
        viewModel.selectMyInfoData()//최초에 한번 db 에서 데이터 긁어라..
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            HomeDrawer(
                closeDrawer = { coroutineScope.launch { drawerState.close() }},
                navigate = { navigate(it) },
                email = infoState.value?.email,
                photo = infoState.value?.photo
            )
        }
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                Box(
                    modifier = Modifier.fillMaxWidth()
                ){
                    AnimatedVisibility(
                        visible = !isSearchActive,
                        enter = expandHorizontally() + fadeIn(),
                        exit = shrinkHorizontally() + fadeOut()
                    ) {
                        HomeAppBar(
                            openDrawer = { coroutineScope.launch { drawerState.open() }},
                            showSearchbar = { isSearchActive = true }
                        )
                    }
                    AnimatedVisibility(
                        visible = isSearchActive,
                        enter = expandHorizontally() + fadeIn(),
                        exit = shrinkHorizontally() + fadeOut()
                    ) {
                        HomeSearchAppbar(
                            onSearch = { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() },
                            closeSearchbar = { isSearchActive = false }
                        )
                    }
                }
            }
        ) { innerPadding ->
            HomeContent(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }

}