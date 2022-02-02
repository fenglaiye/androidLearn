package com.example.androidLearn

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class NewsContentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_content)
        val title = intent.getStringExtra("news_title")
        val content = intent.getStringExtra("news_content")
        if (title != null && content != null) {
            val fragment = supportFragmentManager.findFragmentById(R.id.newsContentFrag) as NewsContentFragment
            fragment.refresh(title, content)
        }
    }

    companion object {
        fun actionStart(context: Context, title: String, Content: String) {
            val intent = Intent(context, NewsContentActivity::class.java)
            intent.putExtra("news_title", title)
            intent.putExtra("news_content", Content)
            context.startActivity(intent)
        }
    }
}