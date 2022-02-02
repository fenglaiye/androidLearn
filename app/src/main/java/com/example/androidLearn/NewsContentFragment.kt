package com.example.androidLearn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.Group
import androidx.fragment.app.Fragment
import com.example.androidLearn.databinding.NewsContentFlagBinding

private lateinit var binding: NewsContentFlagBinding

class NewsContentFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NewsContentFlagBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun refresh(title: String, content: String) {
        val contentLayout = activity?.findViewById<Group>(R.id.contentLayout)
        contentLayout?.visibility = View.VISIBLE

//        binding.contentLayout.visibility = View.VISIBLE
//        binding.apply {
//            contentLayout.visibility = View.VISIBLE
//            newsTitle.text = title
//            newsContent.text = content
//        }
    }
}