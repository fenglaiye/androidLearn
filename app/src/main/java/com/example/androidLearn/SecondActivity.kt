package com.example.androidLearn

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.androidLearn.databinding.SecondLayoutBinding

private lateinit var binding: SecondLayoutBinding

class SecondActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SecondLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button2.setOnClickListener {
//            val intent = Intent("android.intent.action.MAIN")
//            intent.addCategory("android.intent.category.LAUNCHER")
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }

        Log.d(Global.TAG, "Task id2 is $taskId")
//        val extraData = intent.getStringExtra("extra_data")
        val data1 = intent.getStringExtra("param1")
        val data2 = intent.getStringExtra("param2")
        Log.d(Global.TAG, "second data is $data1 $data2")

    }

    companion object {
        fun actionStart(context: Context, data1: String, data2: String) {
            val intent = Intent(context, SecondActivity::class.java)
            intent.putExtra("param1", data1)
            intent.putExtra("param2", data2)
            context.startActivity(intent)
        }
    }

    override fun onBackPressed() {
        val intentRes = Intent().apply {
            putExtra("result", "Hello Result")
        }
        setResult(Activity.RESULT_OK, intentRes)
        Toast.makeText(this, "接管back", Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(Global.TAG, "Second on Destroy")
    }
}
