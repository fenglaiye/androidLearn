package com.example.internet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.androidLearn.Global
import com.example.androidLearn.databinding.ActivityMainInternetBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private lateinit var binding: ActivityMainInternetBinding

class MainActivityInternet5 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainInternetBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.sendRequestBtn.setOnClickListener {
            sendRequest1()
        }
    }

    private fun sendRequest1() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.3.247/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val appService = retrofit.create(AppService::class.java)
        appService.getAppData().enqueue(object : Callback<List<App>> {
            override fun onResponse(call: Call<List<App>>, response: Response<List<App>>) {
                val list = response.body()
                if (list != null) {
                    for (app in list) {
                        Log.d(Global.TAG, "id is ${app.id}")
                    }
                }
            }

            override fun onFailure(call: Call<List<App>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    private fun showResponse(response: String) {
        runOnUiThread {
            // 在这里进行UI操作
            binding.responseText.text = response
        }
    }

}
