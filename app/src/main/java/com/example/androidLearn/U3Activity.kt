package com.example.androidLearn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.androidLearn.databinding.U3LayoutBinding
import java.lang.StringBuilder

private val data = mapOf(
    "Apple" to R.drawable.apple_pic,
    "Banana" to R.drawable.banana_pic,
    "Orange" to R.drawable.orange_pic,
    "Watermelon" to R.drawable.watermelon_pic,
    "Pear" to R.drawable.pear_pic,
    "Grape" to R.drawable.grape_pic,
    "Pineapple" to R.drawable.pineapple_pic,
    "Strawberry" to R.drawable.strawberry_pic,
    "Cherry" to R.drawable.cherry_pic,
    "Mango" to R.drawable.mango_pic
)

private lateinit var binding: U3LayoutBinding

class U3Activity : AppCompatActivity() {
    private val fruitList = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = U3LayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initFruits()
        // 线性的垂直或水平布局
//        val layoutManager = LinearLayoutManager(this)
//        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        // 网格布局
//        val layoutManager = GridLayoutManager(this, 3)
        // 瀑布流布局
        val layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        binding.recycler1.layoutManager = layoutManager
        val adapter = FruitAdapterRecycler(fruitList)
        binding.recycler1.adapter = adapter
    }

    private fun initFruits() {
        repeat(100) {
            fruitList.apply {
                for ((name, pic) in data) {
                    add(Fruit(getRandomLengthString(name), pic))
                }
            }
        }
    }

    private fun getRandomLengthString(str: String): String {
        val n = (1..20).random()
        return StringBuilder().run {
            repeat(n) {
                append(str)
            }
            toString()
        }
    }
}