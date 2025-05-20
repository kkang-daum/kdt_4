package com.example.ch8.section3

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ch8.R
import com.example.ch8.databinding.ActivityTest31Binding
import com.example.ch8.section2.MyAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        //interface 함수 호출해서.. 네트워킹이 가능한 Call 객체 획득..
        val call: Call<PageListModel> = MyApplication.retrofitService.getList(
            "travel",
            MyApplication.API_KEY,
            1,
            10
        )
        //네트워킹.... 콜백 등록해서..
        call.enqueue(object: Callback<PageListModel>{
            override fun onResponse(call: Call<PageListModel>, response: Response<PageListModel>) {
                if(response.isSuccessful){
                    binding.main.layoutManager = LinearLayoutManager(this@Test3_1Activity)
                    binding.main.adapter = MyAdapter(this@Test3_1Activity, response.body()?.articles)
                }
            }

            override fun onFailure(call: Call<PageListModel>, t: Throwable) {
                Log.d("kkang", "$t")
            }
        })
    }
}