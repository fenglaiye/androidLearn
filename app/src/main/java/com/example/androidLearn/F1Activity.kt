package com.example.androidLearn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.androidLearn.databinding.F1LayoutBinding
import com.example.androidLearn.databinding.LeftFragmentBinding

private lateinit var binding: F1LayoutBinding
private lateinit var bindingLeft: LeftFragmentBinding

class F1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = F1LayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(RightFragment())
        bindingLeft = LeftFragmentBinding.inflate(layoutInflater)
        bindingLeft.f1Btn1.setOnClickListener {
            replaceFragment(AnotherRightFragment())
            // 不执行
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.rightFlagLayout, fragment)
        transaction.commit()
    }
}