package com.example.service

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidLearn.databinding.ActivityMainServiceBinding

private lateinit var binding: ActivityMainServiceBinding

class MainActivityService : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}