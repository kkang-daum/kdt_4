package com.example.ch8.section1

import android.Manifest
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import android.os.Bundle
import android.telephony.SubscriptionInfo
import android.telephony.SubscriptionManager
import android.telephony.TelephonyCallback
import android.telephony.TelephonyCallback.CallStateListener
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch8.R
import com.example.ch8.databinding.ActivityTest11Binding

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
            ActivityResultContracts.RequestMultiplePermissions()
        ){
            if(it.all { permission -> permission.value == true }){
                Toast.makeText(this, "permission ok..", Toast.LENGTH_SHORT)
                    .show()
            }else {
                Toast.makeText(this, "permission denied..", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.getBtn.setOnClickListener {
            if(ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_PHONE_STATE
            ) == PackageManager.PERMISSION_GRANTED  &&
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_PHONE_NUMBERS
                ) == PackageManager.PERMISSION_GRANTED){

                //전화기로서의 각종 정보..
                val telephonyManager = getSystemService(TELEPHONY_SERVICE) as TelephonyManager
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
                    telephonyManager.registerTelephonyCallback(
                        mainExecutor,
                        //인터페이스는 상태변경 감지하고자 하는 것을....
                        object: TelephonyCallback(), CallStateListener {
                            override fun onCallStateChanged(state: Int) {
                                when(state){
                                    TelephonyManager.CALL_STATE_IDLE -> {
                                        Log.d("kkang", "idle....")
                                    }
                                    TelephonyManager.CALL_STATE_OFFHOOK -> {
                                        Log.d("kkang", "offhook...")
                                    }
                                    TelephonyManager.CALL_STATE_RINGING -> {
                                        Log.d("kkang", "ringing...")
                                    }
                                }
                            }
                        }
                    )
                }
                //유저 폰 번호 추출....
                var phoneNumber = "unKnown"
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                    val subManager = getSystemService(TELEPHONY_SUBSCRIPTION_SERVICE)
                        as SubscriptionManager
                    for(info: SubscriptionInfo in subManager.activeSubscriptionInfoList!!){
                        val id = info.subscriptionId
                        phoneNumber = subManager.getPhoneNumber(id)
                    }
                }else {
                    phoneNumber = telephonyManager.line1Number
                }

                Log.d("kkang", phoneNumber)

                getRequestNetwork()
            }else {
                launcher.launch(arrayOf(Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.READ_PHONE_NUMBERS))
            }
        }
    }

    private fun getRequestNetwork(){
        //상태 파악하고자 하는 네트웍 정보..
        val networkReq = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)//데이터 통신..
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)//이통사 망..
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)//wifi
            .build()

        val conManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        conManager.requestNetwork(networkReq, object : ConnectivityManager.NetworkCallback(){
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                Log.d("kkang", "NetworkCallback... onAvailable....")
            }

            override fun onUnavailable() {
                super.onUnavailable()
                Log.d("kkang", "NetworkCallback... onUnavailable....")
            }
        })
    }
}