package com.example.ch2.section1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.ch2.R

class Test1_2Activity : AppCompatActivity() {

    //코드에서 필요한 순간 직접 생성..
//    val dataStore: DataStore<Preferences> = PreferenceDataStoreFactory.create(
//        produceFile = { preferencesDataStoreFile("my_prefs")}
//    )

    //위처럼 객체를 생성할 수도 있지만 by 위임으로 생성하는 것이 일반적..
    //by 에 의해 생성하면 singleton 을 보장한다..


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_test12)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}