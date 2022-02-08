package com.example.internet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.androidLearn.Global
import com.example.androidLearn.databinding.ActivityMainInternetBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import java.lang.Exception
import kotlin.concurrent.thread

private lateinit var binding: ActivityMainInternetBinding

class MainActivityInternet4 : AppCompatActivity() {
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
                    .url("http://192.168.3.247/get_data.json")
                    .build()
                val response = client.newCall(request).execute()
//                val responseData = response.body?.string()
//                if (responseData != null) {
//                    parseJson2(responseData)
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

    private fun parseJson1(jsonData: String) {
        try {
            val jsonArray = JSONArray(jsonData)
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val id = jsonObject.getString("id")
                val name = jsonObject.getString("name")
                val version = jsonObject.getString("version")
                Log.d(Global.TAG, "$id $name $version")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun parseJson2(jsonData: String) {
        try {
            val gson = Gson()
            val typeOf = object : TypeToken<List<App>>() {}.type
            val appList = gson.fromJson<List<App>>(jsonData, typeOf)
            for (app in appList) {
                Log.d(Global.TAG, "${app.id} ${app.name} ${app.version}")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
