package com.example.ch1.section1_fragment

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.ch1.OneFragment
import com.example.ch1.R
import com.example.ch1.TwoFragment
import com.example.ch1.databinding.ActivityTest12Binding

class Test1_2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityTest12Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //초기 fragment 를 출력...
        val fragmentManager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        val fragment = OneFragment()
        transaction.add(R.id.fragmentContainer, fragment)
        transaction.commit()//이 순간 화면에 출력..

        binding.fragmentButton.setOnClickListener {
            //TwoFragment 로 화면 교체.. 화면 전환 효과...
            //아래처럼 작성하면 java.lang.IllegalStateException: commit already called 에러 발생..
            //transaction 으로 화면에 출력할 fragment 를 제어하는 것은 맞지만..
            //한번 transaction 을 commit 시키면 transaction 은 close 된다.. 다시 사용 불가하다..
            //다시 transaction 을 얻어서 제어해야 한다..
//            val fragment = TwoFragment()
//            transaction.replace(R.id.fragmentContainer, fragment)
//            transaction.commit()

            //아래의 코드로 TwoFragment 가 나오기는 하지만..
            //back button 에 의해 이전 화면(OneFragment) 가 나오지는 않는다..
            //액티비티가 종료된다..
            //fragment 가 뭐가 실행되었었는지.. stack(history) 정보가 유지되지 않기 때문이다..
//            val transaction2 = fragmentManager.beginTransaction()
//            val fragment = TwoFragment()
//            transaction2.replace(R.id.fragmentContainer, fragment)
//            transaction2.commit()

            //stack 정보가 유지되게.. commit() 직전에 한줄 추가해 주면 된다..
            val transaction2 = fragmentManager.beginTransaction()
            val fragment = TwoFragment()
            transaction2.replace(R.id.fragmentContainer, fragment)
            //null 대신 임의 문자열을 지정해도 된다.. 문자열이 스택정보의 식별자가 되어..
            //복잡한 앱을 만드는 경우.. fragment 의 back stack 을 여러개 만들 수 있다..
            //일반적으로 null 로 지정하면 back 에 의해 이전 fragment 로 되돌아가기만 하면 되는 경우..
            transaction2.addToBackStack(null)
            transaction2.commit()
        }
    }
}