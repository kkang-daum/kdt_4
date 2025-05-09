package com.example.ch1.section4_drawer

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch1.R
import com.example.ch1.databinding.ActivityTest41Binding

class Test4_1Activity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle

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

        //actionbar 에 들어가는 내용이 개발자 뷰인 toolbar 에 그대로 적용하면 된다...
        setSupportActionBar(binding.toolbar)

        //지정한 문자열은 화면 출력과는 상관없다. 상태를 표시하는 문자열이다..
        toggle = ActionBarDrawerToggle(this, binding.main, R.string.drawer_open,
            R.string.drawer_close)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()

        //NavigationView 의 menu 로 준비한 항목 클릭 이벤트....
        binding.mainDrawerView.setNavigationItemSelectedListener {
            Log.d("kkang", "navigation item click.... ${it.title}")
            true
        }
    }

    //메뉴 이벤트 처리 함수...
    //toggle 이 내부적으로는 메뉴이다..
    //toggle 클릭시에 메뉴 이벤트 함수 호출.... 메뉴 이벤트 처리가 실행되지 않고.. toggle 에 준비된
    //drawer 제어 이벤트 처리가 되게 해주어야 한다..
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}