package com.example.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.androidLearn.R
import com.example.androidLearn.databinding.BroadMainActivityBinding

private lateinit var binding: BroadMainActivityBinding

class BroadMainActivity : AppCompatActivity() {
    lateinit var timeChangeReceiver: TimeChangeReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = BroadMainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.broadBtn1.setOnClickListener {
            val intent = Intent("com.example.broadcast.MY_BROADCAST")
            intent.setPackage(packageName)
            // 发送标准广播
//            sendBroadcast(intent)
            // 发送有序广播
            sendOrderedBroadcast(intent, null)
        }
//        val intentFilter = IntentFilter()
//        intentFilter.addAction("android.intent.action.TIME_TICK")
//        timeChangeReceiver = TimeChangeReceiver()
//        registerReceiver(timeChangeReceiver, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
//        unregisterReceiver(timeChangeReceiver)
    }

    inner class TimeChangeReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            Toast.makeText(context, "Time has changed", Toast.LENGTH_SHORT).show()
        }
    }
}