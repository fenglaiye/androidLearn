package com.example.androidLearn

import android.content.Intent
import android.net.Uri
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
            // 显式Intent
//            val intent = Intent(this, SecondActivity::class.java)
            // 隐式Intent
            val intent = Intent("com.example.androidLearn.ACTION_START")
            intent.addCategory("com.example.androidLearn.MY_CATEGORY")
            startActivity(intent)
        }
        binding.button2.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.baidu.com")
            startActivity(intent)
        }
        binding.button3.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:10086")
            startActivity(intent)
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
