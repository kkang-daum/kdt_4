package com.example.ch3.section5

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import com.example.ch3.R
import com.example.ch3.databinding.ActivityTest51Binding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class Test5_1Activity : AppCompatActivity(), CoroutineScope {

    lateinit var dao: UserDAO
    lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

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

        job = Job()

        //database 초기화.. 앱 내에서 한번만 하면 된다..
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "test"
        )
            //room 을 이용한 dbms 작업(dao 함수 호출) 은 가급적 background thread 에 의해
            //실행될 것을 권장..
            //main thread 에 의해 실행됨을 허용하라..
            .allowMainThreadQueries()
            .build()

        //필요한 곳에서 dao 획득해서.. 함수호출해서.. 원하는 dbms 실행하면 된다..
        dao = db.userDao()

        binding.button.setOnClickListener {
            //id 를 모두 0으로 지정한 이유는 Entity 클래스내에 id 가 primary key 로 선언되어
            //있고 auto generate 로 선언되어 있어서.. insert 시에 자동 값 증가되기 때문에..
            val user1 = User(0, "gildong", "hong")
            val user2 = User(0, "gildong2", "hong2")
            val user3 = User(0, "gildong3", "hong3")

            dao.insertUser(user1)
            dao.insertUser(user2)
            dao.insertUser(user3)

            var resultTxt = ""

            dao.getAll().forEach {
                resultTxt += "$it \n"
            }
            binding.resultView.text = resultTxt
        }
    }
}