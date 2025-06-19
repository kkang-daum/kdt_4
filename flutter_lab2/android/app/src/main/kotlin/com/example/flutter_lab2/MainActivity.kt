package com.example.flutter_lab2

import android.util.Log
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.BasicMessageChannel
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.StringCodec
import io.flutter.plugins.GeneratedPluginRegistrant

class MainActivity : FlutterActivity(){
    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        GeneratedPluginRegistrant.registerWith(flutterEngine)

        //message channel..............................
        val channel = BasicMessageChannel<String>(flutterEngine.dartExecutor, "myMessageChannel",
            StringCodec.INSTANCE)
        //message : 전달된 데이터..
        //reply : 결과 리턴 객체..
        channel.setMessageHandler { message, reply ->
            Log.d("kkang", "receive message : $message")
            reply.reply("Reply from Android")
            //테스트 용이성을 위해서.. 요청 받자 마다.. dart 에 다시 요청..
            channel.send("Hello from Android"){ result ->
                //dart 의 리턴...
                Log.d("kkang", "reply : $result")
            }
        }

        //method channel................................
        val methodChannel = MethodChannel(flutterEngine.dartExecutor.binaryMessenger,
            "myMethodChannel")
        methodChannel.setMethodCallHandler { call, result ->
            Log.d("kkang", "111111111111111")
            if(call.method == "oneMethod"){
                val map = call.arguments as Map<String, String>
                Log.d("kkang", "${map.get("Username")}, ${map.get("Password")}")
                result.success(mapOf("one" to 10, "two" to 20));

                //navtive -> dart......
                methodChannel.invokeMethod("twoMethod", "Hello from Android")
            }
        }
    }
}
