//package com.example.service
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.os.Handler
//import android.os.Looper
//import android.os.Message
//import com.example.androidLearn.databinding.ActivityMainServiceBinding
//import kotlin.concurrent.thread
//
//private lateinit var binding: ActivityMainServiceBinding
//
//class MainActivityService : AppCompatActivity() {
//    val updateText = 1
//    val handler = object : Handler(Looper.getMainLooper()) {
//        override fun handleMessage(msg: Message) {
//            // 在这里可进行UI操作
//            when (msg.what) {
//                updateText -> binding.textView.text = "Nice to meet you"
//            }
//        }
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainServiceBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        binding.changeTextBtn.setOnClickListener {
//            thread {
//                val msg = Message()
//                msg.what = updateText
//                handler.sendMessage(msg) // 将msg对象发送出去
//            }
//        }
//    }
//}