package com.example.design

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.androidLearn.Fruit
import com.example.androidLearn.R
import com.example.androidLearn.databinding.ActivityMainDesignBinding
import com.example.util.showToast
import com.google.android.material.snackbar.Snackbar
import kotlin.concurrent.thread

private lateinit var binding: ActivityMainDesignBinding

class MainActivityDesign : AppCompatActivity() {
    val fruits = mutableListOf(
        Fruit("Apple", R.drawable.apple),
        Fruit("Banana", R.drawable.banana),
        Fruit("Orange", R.drawable.orange),
        Fruit("Watermelon", R.drawable.watermelon),
        Fruit("Pear", R.drawable.pear),
        Fruit("Grape", R.drawable.grape),
        Fruit("Pineapple", R.drawable.pineapple),
        Fruit("Strawberry", R.drawable.strawberry),
        Fruit("Cherry", R.drawable.cherry),
        Fruit("Mango", R.drawable.mango)
    )

    val fruitList = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainDesignBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_menu)
        }
        binding.navView.setCheckedItem(R.id.navCall)
        binding.navView.setNavigationItemSelectedListener {
            binding.drawerLayout.closeDrawers()
            true
        }
        binding.fab.setOnClickListener {
            Snackbar.make(it, "Data deleted", Snackbar.LENGTH_SHORT)
                .setAction("Undo") {
                    Toast.makeText(this, "Data restored", Toast.LENGTH_SHORT).show()
                }
                .show()
        }
        initFruits()
        val layoutManager = GridLayoutManager(this, 2)
        val adapter = FruitAdapterCard(this, fruitList)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
        // 下拉刷新
        binding.swipeRefresh.setColorSchemeResources(R.color.design_default_color_primary)
        binding.swipeRefresh.setOnRefreshListener {
            refreshFruits(adapter)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.backup -> "backup".showToast(this)
            R.id.delete -> Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show()
            R.id.settings -> Toast.makeText(this, "settings", Toast.LENGTH_SHORT).show()
            android.R.id.home -> binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        return true
    }

    private fun initFruits() {
        fruitList.clear()
        repeat(1000) {
            val index = (0 until fruits.size).random()
            fruitList.add(fruits[index])
        }
    }

    private fun refreshFruits(adapter: FruitAdapterCard) {
        thread {
            Thread.sleep(2000)
            runOnUiThread {
                initFruits()
                adapter.notifyDataSetChanged()
                binding.swipeRefresh.isRefreshing = false
            }
        }
    }
}