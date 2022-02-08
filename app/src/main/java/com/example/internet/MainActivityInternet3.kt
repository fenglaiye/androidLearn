package com.example.internet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidLearn.databinding.ActivityMainInternetBinding
import okhttp3.OkHttpClient
import okhttp3.Request
import org.xml.sax.InputSource
import java.io.StringReader
import java.lang.Exception
import javax.xml.parsers.SAXParserFactory
import kotlin.concurrent.thread

private lateinit var binding: ActivityMainInternetBinding

class MainActivityInternet3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainInternetBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.sendRequestBtn.setOnClickListener {
            sendRequest1()
        }
    }

    private fun sendRequest1() {
        thread {
            try {
                val client = OkHttpClient()
                val request = Request.Builder()
                    .url("http://192.168.3.247/get_data.xml")
                    .build()
                val response = client.newCall(request).execute()
//                val responseData = response.body?.string()
//                if (responseData != null) {
//                    showResponse(responseData)
//                    parseXML1(responseData)
//                    parseXML2(responseData)
//                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun showResponse(response: String) {
        runOnUiThread {
            // 在这里进行UI操作
            binding.responseText.text = response
        }
    }

    private fun parseXML2(xmlData: String) {
        try {
            val factory = SAXParserFactory.newInstance()
            val xmlReader = factory.newSAXParser().xmlReader
            val handler = ContentHandler()
            //  将ContentHandler实例设置到reader中
            xmlReader.contentHandler = handler
            // 执行解析
            xmlReader.parse(InputSource(StringReader(xmlData)))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}