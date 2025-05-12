package com.example.ch2.section2

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.ch2.R

class BSettingFragment: PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        //아래의 한줄 만으로.. 화면 출력, 설정 내용 저장 자동화..
        setPreferencesFromResource(R.xml.setting_b, rootKey)
    }
}