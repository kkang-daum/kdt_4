package com.example.ch5.section2

import android.app.Service
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import android.os.IBinder
import android.util.Log

//JobScheduler에 의해 실행될 background 작업..
class MyJobService : JobService() {

    override fun onStartJob(params: JobParameters?): Boolean {
        Log.d("kkang", "onStartJob...........")
        return false//완벽히 업무가 종료되었어.. onDestory() 바로 호출된다..
        //true : 함수는 종료되었지만 업무는 아직 끝나지 않았어.. 기다려..
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        Log.d("kkang", "onStopJob.............")
        return true
    }
}