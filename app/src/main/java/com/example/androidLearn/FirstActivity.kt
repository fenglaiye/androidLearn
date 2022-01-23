package com.example.androidLearn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.androidLearn.databinding.FirstLayoutBinding

private lateinit var binding: FirstLayoutBinding

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FirstLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button1.setOnClickListener {
            Toast.makeText(this, "You clicked Button 1", Toast.LENGTH_SHORT).show()
        }
    }
}
