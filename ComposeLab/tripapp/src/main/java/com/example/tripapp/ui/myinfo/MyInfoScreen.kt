package com.example.tripapp.ui.myinfo

import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.tripapp.data.myinfo.MyInfoData
import com.example.tripapp.data.myinfo.insertInfo
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyInfoScreen(
    pop: (infoData: MyInfoData) -> Unit,
    viewModel: MyInfoViewModel
) {
    var email by remember { mutableStateOf("a@a.com") }
    var phone by remember { mutableStateOf("1111-2222") }
    var photo by remember { mutableStateOf("") }

    //lifecycle owner 획득.. 상황에 따라서.. 다른 객체일 수 있다.
    //activity, fragment
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var cameraFileUri: Uri? = null
    var tempFilePath: String? = null

    //최초에 한번 실행되는 코드..
    //viewModel 에 일을 시켜서.. B/L 실행되게 하고.. 결과 데이터를 초기 출력..
    LaunchedEffect(true) {
        val viewModelInfoData = viewModel.getMyInfoData()
        email = viewModelInfoData?.email ?: ""
        phone = viewModelInfoData?.phone ?: ""
        photo = viewModelInfoData?.photo ?: ""
    }

    //컴포저블에서 다른 액티비티 실행.. 결과 되돌려 받기..
    val imageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        try{
            //gallery app 에서 되돌아 온 순간..
            var uri = it.data!!.data!!
            val proj = arrayOf(MediaStore.Images.Media.DATA)
            val cursor = context.contentResolver.query(
                uri, proj, null, null, null
            )
            //file 경로를 gallery app 에게 받아서..
            cursor?.let {
                if(cursor.moveToFirst()){
                    photo = cursor.getString(0)
                }
            }
            //되돌아오자 마자.. 화면에도 출력되어야 해서..
            imageUri = uri
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    //퍼미션 조정... launcher.................
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if(it){
            //gallery app 실행..
            val intent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            imageLauncher.launch(intent)
        }
    }

    //camera app 연동시의 파일 준비..
    fun createImageFile(): File {
        val stamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG_${stamp}_"
        return File.createTempFile(
            imageFileName,
            ".jpg",
            context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        )
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) {
        if(it){
            imageUri = cameraFileUri
            photo = tempFilePath ?: ""
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                actions = {
                    TextButton(
                        onClick = {
                            //저장 데이터 준비..
                            val infoData = MyInfoData(0, email, phone, photo)
                            //ViewModel 에게 일 시켜서.. db 에 데이터 저장..
                            viewModel.updateMyInfoData(infoData)
                            //결과에 따라 화면 다르게 처리..
                                .observe(lifecycleOwner){
                                    if(it){
                                        //성공했으면.. 이전화면으로 자동 전환..
                                        pop(infoData)
                                    }else{
                                        //실패....
                                        Toast.makeText(context, "데이터 저장 실패", Toast.LENGTH_SHORT)
                                            .show()
                                    }
                                }
                        }
                    ) {
                        Text("저장")
                    }
                }
            )
        }
    ) {

    }

}