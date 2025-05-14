package com.example.ch3.section3

import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ch3.R
import com.example.ch3.databinding.ActivityTest31Binding

class Test3_1Activity : AppCompatActivity() {

    lateinit var binding: ActivityTest31Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityTest31Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.toastBtn.setOnClickListener {
            Toast.makeText(this, "show toast", Toast.LENGTH_SHORT).show()
        }

        binding.sumBtn.setOnClickListener {

            //ANR 문제가 발생하는 코드...........................
            //ANR in com.example.ch3 (com.example.ch3/.section3.Test3_1Activity)
            //Reason: Input dispatching timed out (6c4b109 com.example.ch3/com.example.ch3.section3.Test3_1Activity (server) is not responding. Waited 5001ms for MotionEvent).
//            var sum = 0L
//            for(i in 1..20_000_000_000){
//                sum += i
//            }
//            Toast.makeText(this, "$sum", Toast.LENGTH_SHORT).show()

            //ANR 문제를 해결하기 위해서 시간이 오래 걸리는 작업을 Thread 로 진행.............
            //ANR 은 해결이 되지만... 개발자 Thread 에서 화면 출력.. 에러 발생..
//            val obj = object: Runnable {
//                override fun run() {
//                    var sum = 0L
//                    for(i in 1..20_000_000_000){
//                        sum += i
//                    }
//                    //에러나는 코드....
//                    Toast.makeText(this@Test3_1Activity, "$sum", Toast.LENGTH_SHORT).show()
//                }
//            }
//            val thread = Thread(obj)
//            thread.start()


            //방법 1 - thread...handler 구조................................
//            val handler = object: Handler(Looper.getMainLooper()){
//                //아래의 함수는 main thread 에 의해 호출된다..
//                //thread 가 sendMessage() 하는 순간..
//                //매개변수는 thread 에서 전달한 데이터..
//                override fun handleMessage(msg: Message) {
//                    if(msg.what == 1){
//                        Toast.makeText(this@Test3_1Activity, "${msg.obj as Long}", Toast.LENGTH_SHORT)
//                            .show()
//                    }
//                }
//            }
//
//            val obj = object: Runnable{
//                override fun run() {
//                    var sum = 0L
//                    for(i in 1..5_000_000_000){
//                        sum += i
//                    }
//                    //데이터 발생.. 개발자 스레드에서 화면 변경 못한다.. main thread 에게 의뢰..
//                    val message = Message()
//                    message.what = 1//요청의 구분자..
//                    message.obj = sum //넘기는 데이터..
//                    handler.sendMessage(message)//요청 순간.. 이 순간.. main thread 동작..
//                }
//            }
//            val thread = Thread(obj)
//            thread.start()

            //방법2 - AsyncTask....
            //Thread, Handler에 의해 처리되어야 할 내용을 AsyncTask 상속 클래스에 담아서 처리..
            //제네픽 타입 : 백
            // 그라운드 작업에 전달될 데이터 타입
            // 스레드에 의해 반복적으로 발생되는 데이터 타입
            // 스레드의 최종 결과 데이터 타입..
            class MyAsyncTask: AsyncTask<Void, Long, Long>(){
                //시간이 오래 걸리는 업무를 담는 함수.. 내부적으로 스레드에 의해 실행된다..
                override fun doInBackground(vararg params: Void?): Long {
                    var sum = 0L
                    for(i in 1..1_000_000_000){
                        sum += i
                    }
                    return sum//이곳에서 리턴 시킨 값이 이 스레드의 최종 결과 값이다..
                    //이 값이 onPostExecute() 의 매개변수에 전달된다..
                }

                override fun onPostExecute(result: Long?) {
                    //main thread 에 의해 실행..
                    //UI
                    Toast.makeText(this@Test3_1Activity, "$result", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            MyAsyncTask().execute()
        }
    }
}