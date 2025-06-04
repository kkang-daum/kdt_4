package com.example.tripapp.ui.about

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalConfiguration
import com.example.tripapp.ui.about.content.LandscapeContent
import com.example.tripapp.ui.about.content.PortraitContent

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier
) {
    //화면 회전의 상태...
    var orientation by remember { mutableStateOf(Configuration.ORIENTATION_PORTRAIT) }

    //view 로 개발을 하면.. 화면 회전을 코드에서 감지할 필요가 없는..
    //layout 폴더명에 등록만 하면...
    //compose 로 개발하면.. 화면회전에 따른.. 적절한 composable 을 출력해야 함으로..
    //화면회전을 코드에서 감지해서.. 제어..
    //화면 회전을 포함한.. 현 기기의 다양한 현재의 상태..
    val configuration = LocalConfiguration.current

    //이 컴포저블이 화면에 나올때.. 최초에...한번 실행시켜야 하는 업무(network, dbms)
    //이 컴포저블에 상태가 선언되어 있다.. 상태 변경시에 re-composition 된다.. 그때는 할 필요가 없다면?
    //특정 상황에서만.. 다시 업무가 실행되면 된다면...
    //configuration 이 변경 될때마다..

    //{ } 부분이 이 컴포즈가 최초 초기화 될때 한번 실행.. 그 이후 key1 부분이 변경될 때마다 실행
    //true 로 지정했음으로.. 절대 변경은 없다.. 최초에 한번만..
//    LaunchedEffect(true) { }

    //최초에 한번 실행되고.. myFlag 값이 변경될 때마다..
    //LaunchedEffect() 에 지정한 값이 변경되는 순간마다..
//    var myFlag = true
//    LaunchedEffect(myFlag) { }

    LaunchedEffect(configuration) {
        //orientation 이라는 상태값을 변경한다.. 직접 변경하면 되는데..
        //상태 값을 flow 로 발행도 된다..
        snapshotFlow { configuration.orientation }
            .collect { orientation = it }
    }

    Scaffold { innerPadding ->
        when(orientation){
            Configuration.ORIENTATION_LANDSCAPE -> {
                LandscapeContent(modifier = Modifier.padding(innerPadding))
            }
            else -> {
                PortraitContent(modifier = Modifier.padding(innerPadding))
            }
        }
    }

}