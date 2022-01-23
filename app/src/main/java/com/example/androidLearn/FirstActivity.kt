package com.example.androidLearn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.androidLearn.databinding.FirstLayoutBinding

private lateinit var binding: FirstLayoutBinding

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FirstLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button1.setOnClickListener {
            Toast.makeText(this, R.string.button_1_click, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_item -> Toast.makeText(this, R.string.menu_add_click, Toast.LENGTH_SHORT).show()
            R.id.remove_item -> Toast.makeText(this, R.string.menu_remove_click, Toast.LENGTH_SHORT).show()
        }
        return true
    }
}
