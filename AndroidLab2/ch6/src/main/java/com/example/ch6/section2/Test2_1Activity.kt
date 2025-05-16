package com.example.ch6.section2

import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch6.R
import com.example.ch6.databinding.ActivityTest21Binding

class Test2_1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityTest21Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val launcher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            if(it.resultCode == RESULT_OK){
                //주소록쪽의 provider 를 이용할 수 있는 url 이 결과로 넘어온다..
                //url 의 마지막 segment 가 유저가 선택한 데이터의 식별자이다..
                Log.d("kkang", "${it.data?.data}")
                //provider 에게 구체적으로 원하는 데이터 요청...
                val cursor: Cursor? = contentResolver.query(
                    it.data!!.data!!,
                    arrayOf(
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                        ContactsContract.CommonDataKinds.Phone.NUMBER
                    ),
                    null, null, null
                )
                if(cursor!!.moveToFirst()){
                    binding.resultView.text =
                        "name : ${cursor?.getString(0)}, phone : ${cursor.getString(1)}"
                }
            }
        }
    }
}