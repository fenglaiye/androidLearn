package com.example.androidLearn

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.androidLearn.databinding.FirstLayoutBinding

private lateinit var binding: FirstLayoutBinding

class FirstActivity : AppCompatActivity() {
    private val firstActivityLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
        if (activityResult.resultCode == Activity.RESULT_OK) {
            val result = activityResult.data?.getStringExtra("result")
            Toast.makeText(this, result, Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FirstLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button1.setOnClickListener {
            Log.d(Global.TAG, "Task id1 is $taskId")
            // 显式Intent
//            val intent = Intent(this, SecondActivity::class.java)
            // 隐式Intent
//            val intent = Intent("com.example.androidLearn.ACTION_START")
//            intent.addCategory("com.example.androidLearn.MY_CATEGORY")
//            val intent = Intent(this, SecondActivity::class.java)
//            intent.putExtra("extra_data", "Hello Second")
//            firstActivityLauncher.launch(intent)
            SecondActivity.actionStart(this, "data1", "data2")
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

        if (savedInstanceState != null) {
            val tempData = savedInstanceState.getString("data_key")
            Log.d(Global.TAG, "tempData is $tempData")
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("data_key", "data_value")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(Global.TAG, "First on Restart")
    }
}
