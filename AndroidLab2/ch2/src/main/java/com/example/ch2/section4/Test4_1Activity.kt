package com.example.ch2.section4

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch2.R
import com.example.ch2.databinding.ActivityTest41Binding

class Test4_1Activity : AppCompatActivity() {
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
    }
}