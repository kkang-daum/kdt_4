package com.example.ch3.mission2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import com.example.ch3.R
import com.example.ch3.mission2.fragments.ListFragment

class MissionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mission)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        supportFragmentManager.beginTransaction().run {
//            add(R.id.main, ListFragment())
//            commit()
//        }

        val navHost = NavHostFragment.create(R.navigation.mission_nav_graph)
        supportFragmentManager.beginTransaction()
            .replace(R.id.main, navHost)
            .setPrimaryNavigationFragment(navHost)
            .commit()
    }
}