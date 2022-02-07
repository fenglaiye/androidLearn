package com.example.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import com.example.androidLearn.Global
import com.example.androidLearn.databinding.ActivityMainServiceBinding

private lateinit var binding: ActivityMainServiceBinding

class MainActivityService : AppCompatActivity() {
    lateinit var downloadBinder: MyIntentService.DownloadBinder

    private val connection = object : ServiceConnection {
        // 创建匿名类实例使用object关键字
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            downloadBinder = service as MyIntentService.DownloadBinder
            downloadBinder.startDownload()
            downloadBinder.getProgress()
        }

        override fun onServiceDisconnected(name: ComponentName) {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.startServiceBtn.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            startService(intent) // 启动Service
        }
        binding.stopServiceBtn.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            stopService(intent) // 停止Service
        }
        binding.bindServiceBtn.setOnClickListener {
            val intent = Intent(this, MyIntentService::class.java)
            bindService(intent, connection, Context.BIND_AUTO_CREATE) // 绑定
        }
        binding.unbindServiceBtn.setOnClickListener {
            unbindService(connection) // 解绑
        }
        binding.startIntentServiceBtn.setOnClickListener {
            // 打印主线程id
            Log.d(Global.TAG, "thread 主线程 id is ${Thread.currentThread().name}")
            val intent = Intent(this, MyIntentService::class.java)
            startService(intent)
        }
    }
}