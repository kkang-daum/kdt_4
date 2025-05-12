package com.example.ch2.section3

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch2.R
import com.example.ch2.databinding.ActivityTest31Binding
import java.io.File

class Test3_1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityTest31Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.internalBtn.setOnClickListener {
            //내장 메모리...
            val file = File(filesDir, "test.txt")//내장 메모리 루트의 test.txt
            val writeStream = file.writer()
            writeStream.write("hello world - internal")
            writeStream.flush()

            val readStream = file.reader().buffered()
            readStream.forEachLine {
                binding.result.text = it
            }
        }

        binding.externalBtn.setOnClickListener {
            //외장. 앱별...
            val file = File(getExternalFilesDir(null), "test.txt")//외장 앱별 메모리 루트의 test.txt
            val writeStream = file.writer()
            writeStream.write("hello world - external")
            writeStream.flush()

            val readStream = file.reader().buffered()
            readStream.forEachLine {
                binding.result.text = it
            }
        }
    }
}