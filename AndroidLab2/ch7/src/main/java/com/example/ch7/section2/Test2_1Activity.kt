package com.example.ch7.section2

import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch7.R
import com.example.ch7.databinding.ActivityTest21Binding
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnSuccessListener

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
        binding.locationBtn.setOnClickListener {
            //위치 정보 획득 api
            val providerClient: FusedLocationProviderClient =
                LocationServices.getFusedLocationProviderClient(this)

            //GoogleApiClient 에 등록할 콜백 준비...
            //위치 정보 제공자가 결정되거나.. 이용하던 제공자가 이용 불가능 상태의 콜백..
            val connectionCallback = object: GoogleApiClient.ConnectionCallbacks {
                //이용 가능시의 callback..
                override fun onConnected(p0: Bundle?) {
                    //위치 획득...
                    if(ContextCompat.checkSelfPermission(
                        this@Test2_1Activity,
                        "android.permission.ACCESS_FINE_LOCATION"
                    ) == PackageManager.PERMISSION_GRANTED){
                        providerClient.lastLocation.addOnSuccessListener(
                            this@Test2_1Activity,
                            object : OnSuccessListener<Location> {
                                override fun onSuccess(p0: Location?) {
                                    val latitude = p0?.latitude
                                    val longitude = p0?.longitude

                                    binding.resultView.text = "$latitude, $longitude"
                                }
                            }
                        )
                    }
                }
                //이용하던 제공자가 이용 불가능 상태로 변하는 순간..
                override fun onConnectionSuspended(p0: Int) {
                    Toast.makeText(this@Test2_1Activity, "일시 제공자 불능", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            //현 시점 디바이스에 이용 가능 제공자 없을 때 콜백..
            val failedCallback = object: GoogleApiClient.OnConnectionFailedListener {
                override fun onConnectionFailed(p0: ConnectionResult) {
                    Toast.makeText(this@Test2_1Activity, "가용할 제공자 없음", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            //GoogleApiClent 초기화 하면서.. 준비한 콜백 등록..
            val apiClient: GoogleApiClient = GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)//play-service 의 다양한 api 중 어느 api 를 사용?
                .addConnectionCallbacks(connectionCallback)
                .addOnConnectionFailedListener(failedCallback)
                .build()

            //위치정보 제공자 결정.... 결과는 각종 등록된 콜백이 호출되면서...
            apiClient.connect()
        }
    }
}