package com.example.androidLearn

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout

class TitleLayout(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    init {
        LayoutInflater.from(context).inflate(R.layout.title, this)
        val btnBack = findViewById<Button>(R.id.titleBtnBack)
        val btnEdit = findViewById<Button>(R.id.titleBtnEdit)
        btnBack.setOnClickListener {
            val activity = context as Activity
            activity.finish()
        }
        btnEdit.setOnClickListener {
            Toast.makeText(context, "You clicked Edit", Toast.LENGTH_SHORT).show()
        }
    }
}