package com.example.ch8.section2

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.ch8.R
import com.example.ch8.databinding.ActivityTest21Binding
import org.json.JSONObject

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

//        q : 검색어..
        val url ="https://newsapi.org/v2/everything?q=travel&apiKey=3dbc3796e7a64890a9bcc16373e72592&page=1&pageSize=5"

        val queue = Volley.newRequestQueue(this)

        val request = object : JsonObjectRequest(
            Method.GET,
            url,
            null,
            Response.Listener<JSONObject> {//성공 callback...
                val jsonArray = it.getJSONArray("articles")
                val mutableList = mutableListOf<ItemModel>()
                for(i in 0 until jsonArray.length()){
                    val article = jsonArray.getJSONObject(i)
                    val model = ItemModel(
                        id = i.toLong(),
                        author = article.getString("author"),
                        title = article.getString("title"),
                        description = article.getString("description"),
                        urlToImage = article.getString("urlToImage"),
                        publishedAt = article.getString("publishedAt")
                    )
                    mutableList.add(model)
                }

                binding.main.layoutManager = LinearLayoutManager(this)
                binding.main.adapter = MyAdapter(this, mutableList)
            },
            Response.ErrorListener { //error callback..
                Log.d("kkang", "$it")
            }
        ){
            //header설정....
            //이 함수가 자동 호출되고.. 이 함수에서 리턴시키는 map 객체로 header 설정되어 서버에 요청..
            override fun getHeaders(): MutableMap<String, String> {
                val map = mutableMapOf<String, String>(
                    "User-agent" to "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36"
                )
                return map
            }
        }

        //서버 연동 시점...
        queue.add(request)
    }
}