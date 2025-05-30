package com.example.ch7.section3

import android.hardware.camera2.CameraOfflineSession
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch7.R
import com.example.ch7.databinding.ActivityTest31Binding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class Test3_1Activity : AppCompatActivity(), OnMapReadyCallback {

    var googleMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityTest31Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val mapFragment = supportFragmentManager.findFragmentById(binding.main.id) as
                SupportMapFragment
        mapFragment.getMapAsync(this)//callback 등록..
    }

    //지도 이용 가능 상황에서 호출되는 함수..
    override fun onMapReady(p0: GoogleMap?) {
       googleMap = p0
       moveMap(37.519, 127.123)
    }

    private fun moveMap(latitude: Double, longitude: Double){
        //지도에서의 위치.. LatLng 객체로...
        val latLng = LatLng(latitude, longitude)
        //지도 위치 옵션...
        val position: CameraPosition = CameraPosition.Builder()
            .target(latLng)//지도 위치...
            .zoom(16f)//초기 zoom level
            .build()

        //지도 center 이동...
        googleMap!!.moveCamera(CameraUpdateFactory.newCameraPosition(position))

        //마커(이미지) 올리기....
        val markerOption = MarkerOptions()
        markerOption.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
        markerOption.position(latLng)
        markerOption.title("MyLocation")
        googleMap?.addMarker(markerOption)
    }
}
//AndroidManifest.xml
//- 퍼미션...
//- Map key
//- play service version



//개발자 컴퓨터에서 SHA1 지문.. 획득...
//앱을 빌드할 대 사용했던 키를 이용해야 한다..
//정식 배포용 앱이라면.. 앱 빌드시에 정식으로 만든 키 이용..
//테스트용은 디버그 키 이용...

//디버그 키 이용...............
//gradle signingReport

//개발자 사이트(console) 에서.. 맵키 얻기...
//https://console.cloud.google.com/

//android map api 사용하겠다고 설정...
//메뉴 -> API 및 서비스 -> 라이브러리
//Maps SDK For Android 클릭..
//"API 사용 설정됨" 으로 만들어야..

//메뉴 -> API 및 서비스 -> 사용자 인증 정보
//"+사용자 인증정보 만들기" -> API 키
//"API 키 수정" 클릭으로 앱 정보 등록..

//"Android 앱" 선택

//package, sha1 등록..
//키 제한 -> Maps SDK for Android 선택
