package edu.tyut.flutterintegration.ui.flutter

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import edu.tyut.flutterintegration.ui.viewmodel.FlutterViewModel
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.BasicMessageChannel
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.JSONMessageCodec
import io.flutter.plugin.common.JSONMethodCodec
import io.flutter.plugin.common.MessageCodec
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.StandardMessageCodec
import io.flutter.plugin.common.StringCodec
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val TAG: String = "FlutterContainerActivity"
private const val CHANNEL = "edu.tyut.flutter/android"
private const val JSON_CHANNEL = "edu.tyut.flutter/json"
private const val STREAM_CHANNEL = "edu.tyut.flutter/stream"

class FlutterContainerActivity : FlutterActivity() {
    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        Log.i(TAG, "configureFlutterEngine...")
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler { call: MethodCall, result: MethodChannel.Result ->
            if (call.method == "getDeviceInfo") {
                val name: String? = call.argument<String>("name")
                val age: Int? = call.argument<Int>("age")
                val deviceInfo: String = "Android ${Build.VERSION.RELEASE} (SDK ${Build.VERSION.SDK_INT})"
                Log.i(TAG, "configureFlutterEngine -> device: info -> name: $name, age: $age")
                result.success(deviceInfo)
            } else {
                result.notImplemented()
            }
        }

        val messageChannel: BasicMessageChannel<Any> = BasicMessageChannel(flutterEngine.dartExecutor.binaryMessenger, JSON_CHANNEL,
            JSONMessageCodec.INSTANCE
        )
        messageChannel.setMessageHandler { message: Any?, reply: BasicMessageChannel.Reply<Any> ->
            // 处理 Flutter 传来的 JSON 数据
            Log.i(TAG, "Received message: $message")
            reply.reply(mapOf("response" to "Android: $message"))
        }

        // 3 stream
        val eventChannel: EventChannel = EventChannel(flutterEngine.dartExecutor.binaryMessenger, STREAM_CHANNEL)
        // 设置 StreamHandler 来处理事件流
        eventChannel.setStreamHandler(object : EventChannel.StreamHandler {
            override fun onListen(arguments: Any?, events: EventChannel.EventSink?) {
                // 在这里发送事件数据
                lifecycleScope.launch{
                    for (i in 0 until 10) {
                        events?.success("Event #$i") // 发送事件到 Flutter
                        delay(1000L) // 模拟每秒发送一次事件
                    }
                }
            }

            override fun onCancel(arguments: Any?) {
                // 取消事件流时的处理
            }
        })
    }
}