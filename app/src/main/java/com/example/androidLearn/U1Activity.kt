package com.example.androidLearn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.androidLearn.databinding.U1LayoutBinding

private lateinit var binding: U1LayoutBinding

class U1Activity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = U1LayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.b1.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.b1 -> {
//                val inputText = binding.e1.text.toString()
//                Toast.makeText(this, inputText, Toast.LENGTH_SHORT).show()
//                binding.i1.setImageResource(R.drawable.i2)
                binding.p1.apply {
//                    visibility = if (visibility == View.VISIBLE) View.GONE else View.VISIBLE
                    progress += 10
                }
                AlertDialog.Builder(this).apply {
                    setTitle("This is Dialog")
                    setMessage("Something ok")
                    setCancelable(true)
                    setPositiveButton("OK") {
                        dialog, which -> Toast.makeText(this@U1Activity, "click OK", Toast.LENGTH_SHORT).show()
                    }
                    setNegativeButton("Cancel") {
                        dialog, which ->
                    }
                    show()
                }
            }
        }
    }
}