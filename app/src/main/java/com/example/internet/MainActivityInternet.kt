package com.example.internet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidLearn.databinding.ActivityMainInternetBinding

private lateinit var binding: ActivityMainInternetBinding

class MainActivityInternet : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainInternetBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.sendRequestBtn.setOnClickListener {
            sendRequest1()
        }
    }

    private fun sendRequest1() {}

    private fun showResponse(response: String) {
        runOnUiThread {
            // 在这里进行UI操作
            binding.responseText.text = response
        }
    }

}
