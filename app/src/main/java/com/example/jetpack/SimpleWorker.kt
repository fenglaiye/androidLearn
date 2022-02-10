package com.example.jetpack

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.androidLearn.Global

class SimpleWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        Log.d(Global.TAG, "do work in SimpleWorker")
        return Result.success()
    }
}