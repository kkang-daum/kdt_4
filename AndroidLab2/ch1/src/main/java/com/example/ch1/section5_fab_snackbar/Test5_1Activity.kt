package com.example.ch1.section5_fab_snackbar

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch1.R
import com.example.ch1.databinding.ActivityTest51Binding
import com.google.android.material.snackbar.Snackbar

class Test5_1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityTest51Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.fab.shrink()//초기상태.. icon 만 나오게..

        binding.fab.setOnClickListener {
            when(binding.fab.isExtended){
                true -> {
                    Snackbar.make(binding.main, "메시지를 확인하시겠습니까?", Snackbar.LENGTH_LONG)
                        .setAction("실행취소"){
                            //액션 이벤트....
                            Toast.makeText(this@Test5_1Activity, "메시지 확인", Toast.LENGTH_SHORT)
                                .show()
                        }
                        .show()
                    binding.fab.shrink()
                }
                false -> binding.fab.extend()
            }
        }
    }
}