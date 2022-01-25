package com.example.androidLearn

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_layout)
        val extraData = intent.getStringExtra("extra_data")
        Log.d("SecondActivity", "extra data is $extraData")
    }

    override fun onBackPressed() {
        val intentRes = Intent().apply {
            putExtra("result", "Hello Result")
        }
        setResult(Activity.RESULT_OK, intentRes)
        Toast.makeText(this, "接管back", Toast.LENGTH_SHORT).show()
        finish()
    }
}
