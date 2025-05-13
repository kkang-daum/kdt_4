package com.example.ch2.section4

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch2.R
import com.example.ch2.databinding.ActivityTest41Binding
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

class Test4_1Activity : AppCompatActivity() {

    lateinit var filePath: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityTest41Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val thumbnailLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            //데이터 획득.. 이미지 데이터가 직접 전달된다..
            val bitmap = it.data?.extras?.get("data") as Bitmap
            binding.resultView.setImageBitmap(bitmap)
        }

        binding.thumbnailBtn.setOnClickListener {
            //camera app 실행.. thumbnail 방법으로..
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            thumbnailLauncher.launch(intent)
        }

        val fileLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            //원본 파일이 읽힌다.. 파일 사이즈를 코드적으로 줄여서..
            val option = BitmapFactory.Options()
            option.inSampleSize = 10
            val bitmap = BitmapFactory.decodeFile(filePath, option)
            bitmap?.let {
                binding.resultView.setImageBitmap(bitmap)
            }
        }

        binding.fileBtn.setOnClickListener {
            //file 준비...
            val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            val dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            val file = File.createTempFile(
                "JPEG_${timeStamp}_",
                ".jpg",
                dir
            )
            filePath = file.absolutePath

            //camera app 에게 공개할 파일 정보.. Uri
            val uri = FileProvider.getUriForFile(
                this,
                "com.example.ch2.fileprovider",
                file
            )
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
            fileLauncher.launch(intent)
        }
    }
}