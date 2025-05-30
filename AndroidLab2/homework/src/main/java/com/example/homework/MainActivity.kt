package com.example.homework

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ){
        if(it){
            Toast.makeText(this, "모든 권한이 허용되었습니다.", Toast.LENGTH_SHORT)
                .show()
        }else {
            Toast.makeText(this, "일부 권한이 거부되어 앱을 정상적으로 동작시킬 수 없습니다.", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            if(ContextCompat.checkSelfPermission(
                this,
                "android.permission.POST_NOTIFICATIONS"
            ) != PackageManager.PERMISSION_GRANTED){
                permissionLauncher.launch("android.permission.POST_NOTIFICATIONS")
            }
        }

        val scheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
        val builder = JobInfo.Builder(1, ComponentName(this, MyJobService::class.java))
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
        scheduler.schedule(builder.build())
    }
}