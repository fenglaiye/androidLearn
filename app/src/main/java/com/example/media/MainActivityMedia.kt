package com.example.media

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import com.example.androidLearn.R

class MainActivityMedia : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_media)
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        // 8.0系统以上创建渠道
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("normal", "Normal", NotificationManager.IMPORTANCE_DEFAULT)
            val channel2 = NotificationChannel("important", "Important", NotificationManager.IMPORTANCE_HIGH)
            manager.createNotificationChannel(channel)
            manager.createNotificationChannel(channel2)
        }
        findViewById<Button>(R.id.sendNotice).setOnClickListener {
            // 定义点击通知
            val intent = Intent(this, NotificationActivity::class.java)
            val pi = PendingIntent.getActivity(this, 0, intent, 0)
            // 发通知
            val notification = NotificationCompat.Builder(this, "important")
                .setContentTitle("致武超")
//                .setStyle(NotificationCompat.BigTextStyle().bigText("有人问我，我就会讲，但是无人来。我期待到无奈，有话要讲，得不到装载。我的心情犹像樽盖 等被揭开， 嘴巴却在养青苔"))
//                .setStyle(NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(resources, R.drawable.big_image)))
                .setContentText("有人问我，我就会讲，但是无人来。")
                .setSmallIcon(R.drawable.small_icon)
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.i2))
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build()
            manager.notify(1, notification)
        }
    }
}