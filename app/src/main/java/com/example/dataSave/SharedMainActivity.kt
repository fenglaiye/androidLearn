package com.example.dataSave

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.androidLearn.Global
import com.example.androidLearn.R

class SharedMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_shared)
        findViewById<Button>(R.id.saveButton).setOnClickListener {
            val editor = getSharedPreferences("dataShared", Context.MODE_PRIVATE).edit()
//            val editor = getPreferences(0).edit()
            editor.apply {
                putString("name", "Tom Wc")
                putInt("age", 29)
                putBoolean("happy", true)
                apply()
            }
        }

        findViewById<Button>(R.id.restoreButton).setOnClickListener {
            val prefs = getSharedPreferences("dataShared", Context.MODE_PRIVATE)
            prefs.apply {
                Log.d(Global.TAG, "name is ${getString("name", "")}")
                Log.d(Global.TAG, "age is ${getInt("age", 0)}")
                Log.d(Global.TAG, "happy is ${getBoolean("happy", false)}")
            }
        }
    }
}