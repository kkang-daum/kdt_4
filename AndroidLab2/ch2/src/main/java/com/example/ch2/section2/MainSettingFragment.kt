package com.example.ch2.section2

import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.preference.EditTextPreference
import androidx.preference.ListPreference
import androidx.preference.Preference


import androidx.preference.PreferenceFragmentCompat
import com.example.ch2.R

//OnSharedPreferenceChangeListener - 모든 설정 변경 순간의 이벤트를 한곳에서 처리하고 싶을 때..
class MainSettingFragment: PreferenceFragmentCompat(), OnSharedPreferenceChangeListener {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)

        //key 값으로.. 설정 객체 획득..
        val idPreference: EditTextPreference? = findPreference("id")
        val colorPreference: ListPreference? = findPreference("color")

        //summary 동적 지정...
        //저장된 값을 그대로.. summary 에 출력되게 하면 되는 경우....
        colorPreference?.summaryProvider = ListPreference.SimpleSummaryProvider.getInstance()

        //설정값을 획득해, 알고리즘후, summary 문자열을 만들어 지정하고 싶은 경우..
        idPreference?.summaryProvider = Preference.SummaryProvider<EditTextPreference>{ preference ->
            //값 획득..
            val text = preference.text
            if(TextUtils.isEmpty(text)){
                "설정이 되지 않았습니다."
            }else {
                "설정한 ID 는 $text 입니다."
            }
        }

        //설정값이 변경되는 순간.. 이벤트 처리................................
        //설정 값 저장하기 위해서 이벤트 처리할 필요는 없다..
        //저장과 동시에 돌려야 하는 업무가 있는 경우...

        //개별 설정에 직접 이벤트 핸들러 등록하는 경우...
        idPreference?.setOnPreferenceChangeListener{ preference, value ->
            Log.d("kkang", "id changed... $value")
            true
        }
    }

    //모든 설정 객체.. 변경 이벤트...
    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        Log.d("kkang", "$key changed..... ${sharedPreferences?.getString(key, "")}")
    }

    override fun onResume() {
        super.onResume()
        //이벤트 등록
        preferenceManager.sharedPreferences?.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        //이벤트 해제...
        preferenceManager.sharedPreferences?.unregisterOnSharedPreferenceChangeListener(this)
    }

}