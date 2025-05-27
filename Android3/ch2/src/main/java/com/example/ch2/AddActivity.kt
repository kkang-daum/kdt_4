package com.example.ch2

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch2.databinding.ActivityAddBinding
import java.text.SimpleDateFormat
import java.util.Date

class AddActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setSupportActionBar(binding.toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(binding.addEditView.text.isNotEmpty()){
            //저장하기 위한 데이터..
            val data = mapOf(
                "email" to MyApplication.email,
                "content" to binding.addEditView.text.toString(),
                "date" to SimpleDateFormat("yyyy-MM-dd").format(Date())
            )
            //데이터 저장..
            //collection -> document
            MyApplication.db.collection("news")//없으면 만들어지고..
                .add(data)
                .addOnSuccessListener {
                    Toast.makeText(this, "${it.id}가 등록되었습니다.",
                        Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Log.d("kkang", "data save error... $it")
                }
        }else {
            Toast.makeText(this, "데이터가 입력되어야 합니다.", Toast.LENGTH_SHORT)
                .show()
        }
        return super.onOptionsItemSelected(item)
    }
}