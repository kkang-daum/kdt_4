package com.example.ch8.section4

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.ch8.R
import com.example.ch8.databinding.ActivityTest41Binding

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

        //리소스 이미지 출력..................
//        Glide.with(this)
//            .load(R.drawable.seoul)
//            .into(binding.imageView)

        //Gallery 이미지....
//        val launcher = registerForActivityResult(
//            ActivityResultContracts.StartActivityForResult()
//        ){
//            Glide.with(this)
//                .load(it.data!!.data)
//                .into(binding.imageView)
//        }
//
//        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//        intent.type = "image/*"
//        launcher.launch(intent)

        //network.................
        Glide.with(this)
            .load("https://qqi.insider.com/682b87e3c6ad288d1481573e?width=1200&format=jpeg")
            .override(200, 200)
            .placeholder(R.drawable.loading)
            .error(R.drawable.error)
            .into(binding.imageView)
    }
}