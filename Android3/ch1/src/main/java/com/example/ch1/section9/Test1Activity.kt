package com.example.ch1.section9

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch1.R
import com.example.ch1.databinding.ActivityTest1Binding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

//case1... activity 자체를 coroutine scope 로......
class Test1Activity : AppCompatActivity(), CoroutineScope {

    val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityTest1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.button1.setOnClickListener {
            //코루틴을 구동시켜서.. 특정 업무가 진행되게 하고자 한다..
//            CoroutineScope(Dispatchers.Default).launch {
//                var result = 0
//                repeat(5){
//                    result += it
//                    delay(500)
//                }
//
////                //android.view.ViewRootImpl$CalledFromWrongThreadException: Only the original thread that created a view hierarchy can touch its views.
//                //runtime error... view 를 main thread 가 아닌 다른 스레드로.. 접근..
//                binding.textView.text = "result : $result"
//            }


            //test2..... main thread 에서 업무처리.. view handling..
            //==>정상 테스트...
            //==>상당히 비효율적인 코드...
            //모든 부분이 main thread 에 의해 처리...
//            CoroutineScope(Dispatchers.Main).launch {
//                var result = 0
//                repeat(5){
//                    result += it
//                    delay(500)
//                }
//                binding.textView.text = "result : $result"
//            }

            //업무에 따라 dispatcher 를 적절하게 교체해가면서..(새로운 코루틴으로 교체하거나
            //scoping function 으로 교체하거나..
            CoroutineScope(Dispatchers.Default).launch {
                var result = 0
                repeat(5){
                    result += it
                    delay(500)
                }
                launch(Dispatchers.Main) {
                    binding.textView.text = "result : $result"
                }
            }
        }

        binding.button3.setOnClickListener {
            finish()
        }
        binding.button2.setOnClickListener {
            //액티비티가 종료되더라도.. 코루틴은 계속 움직인다...
//            CoroutineScope(Dispatchers.Default).launch {
//                repeat(5){
//                    Log.d("kkang", "coroutine... $it")
//                    delay(1000)
//                }
//            }

            //액티비티가 코루틴 스코프로 선언되어 있다.. 그 스코프를 이용해 본다..
            //동일하게.. 액티비티 종료되더라도.. 계속 동작할 수있다..
            //개발자가.. 적절하게.. 코루틴 라이프사이클을 액티비티 라이프 사이클로 제어해 주어야 한다.
            launch {
                repeat(5){
                    Log.d("kkang", "coroutine... $it")
                    delay(1000)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("kkang", "onDestroy....")
        //이렇게 코루틴을 종료시켜야.. 액티비티 종료시점에..
        //코루틴도 종료..
        job.cancel()
    }
}
