package com.example.internet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.example.androidLearn.databinding.ActivityMainInternetBinding
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

private lateinit var binding: ActivityMainInternetBinding

class MainActivityInternet1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainInternetBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        binding.webView.apply {
//            settings.javaScriptEnabled = true
//            webViewClient = WebViewClient()
//            loadUrl("https://www.baidu.com")
//        }
        binding.sendRequestBtn.setOnClickListener {
            sendRequest1()
        }
    }

    private fun sendRequest1() {
        // 开启线程发起网络请求
        thread {
            var connection : HttpURLConnection? = null
            try {
                val response = StringBuilder()
                val url = URL("https://www.baidu.com")
                connection = url.openConnection() as HttpURLConnection
                connection.connectTimeout = 8000
                connection.readTimeout = 8000
                val input = connection.inputStream
                // 下面对获取到的输入流进行读取
                val reader = BufferedReader(InputStreamReader(input))
                reader.use {
                    reader.forEachLine {
                        response.append(it)
                    }
                }
                showResponse(response.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                connection?.disconnect()
            }
        }
    }

    private fun showResponse(response: String) {
        runOnUiThread {
            // 在这里进行UI操作
            binding.responseText.text = response
        }
    }
}