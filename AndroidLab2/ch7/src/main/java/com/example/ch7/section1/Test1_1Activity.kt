package com.example.ch7.section1

import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.PackageManagerCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch7.R
import com.example.ch7.databinding.ActivityTest11Binding

class Test1_1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityTest11Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val launcher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ){
            if(it){
                Toast.makeText(this, "permission ok..", Toast.LENGTH_SHORT).show()
            }else {
                Toast.makeText(this, "permission denied..", Toast.LENGTH_SHORT).show()
            }
        }

        binding.locationBtn.setOnClickListener {
            if(ContextCompat.checkSelfPermission(
                this,
                "android.permission.ACCESS_FINE_LOCATION"
            ) == PackageManager.PERMISSION_GRANTED){

                val manager = getSystemService(LOCATION_SERVICE) as LocationManager

                var result = "All providers : "
                //이 디바이스에 있는 모든 location provider 목록...
                val providers = manager.allProviders
                for(provider in providers){
                    result += "$provider, "
                }
                Log.d("kkang", result)

                //현재 이용 가능한 location provider 목록...
                result = "Enabled Providers : "
                val enabledProviders = manager.getProviders(true)
                for(provider in enabledProviders){
                    result += "$provider, "
                }
                Log.d("kkang", result)

                //위치 획득....
                val location: Location? = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                location?.let {
                    val latitude = location.latitude
                    val longitude = location.longitude
                    val accuracy = location.accuracy
                    val time = location.time

                    binding.resultView.text = "$latitude, $longitude, $accuracy, $time"

                } ?: {
                    Log.d("kkang", "location null.............")
                }

            }else {
                launcher.launch("android.permission.ACCESS_FINE_LOCATION")
            }

        }
    }
}