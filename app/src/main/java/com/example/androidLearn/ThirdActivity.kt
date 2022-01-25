package com.example.androidLearn

import android.os.Bundle
import android.util.Log
import com.example.androidLearn.databinding.ThirdLayoutBinding

private lateinit var binding: ThirdLayoutBinding

class ThirdActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ThirdLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(Global.TAG, "Task id3 is $taskId")
        binding.button3.setOnClickListener {
            ActivityCollector.finishAll()
        }
    }
}