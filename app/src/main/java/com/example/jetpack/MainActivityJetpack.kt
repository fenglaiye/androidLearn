package com.example.jetpack

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.edit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.androidLearn.databinding.ActivityMainJetpackBinding

private lateinit var binding: ActivityMainJetpackBinding

class MainActivityJetpack : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainJetpackBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sp = getPreferences(Context.MODE_PRIVATE)
        val countReserved = sp.getInt("count_reserved", 0)
        viewModel = ViewModelProvider(this, MainViewModelFactory(countReserved))
            .get(MainViewModel::class.java)
        binding.plusOneBtn.setOnClickListener {
            viewModel.plusOne()
        }
        binding.clearBtn.setOnClickListener {
            viewModel.clear()
        }
        viewModel.counter.observe(this, Observer { count ->
            binding.infoText.text = count.toString()
        })
        lifecycle.addObserver(MyObserver(lifecycle))
    }

    override fun onPause() {
        super.onPause()
        sp.edit {
            putInt("count_reserved", viewModel.counter.value ?: 0)
        }
    }
}