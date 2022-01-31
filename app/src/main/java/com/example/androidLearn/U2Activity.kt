package com.example.androidLearn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidLearn.databinding.U2LayoutBinding

private lateinit var binding: U2LayoutBinding

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

class U2Activity : AppCompatActivity() {
    private val fruitList = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = U2LayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initFruits()
        val adapter = FruitAdapter(this, R.layout.fruit_item, fruitList)
        binding.l1.adapter = adapter
    }

    private fun initFruits() {
        repeat(100) {
            fruitList.apply {
                for ((name, pic) in data) {
                    add(Fruit(name, pic))
                }
            }
        }
    }
}
