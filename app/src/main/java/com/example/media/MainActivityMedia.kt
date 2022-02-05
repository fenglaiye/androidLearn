package com.example.media

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.NotificationCompat
import androidx.core.content.FileProvider
import com.example.androidLearn.R
import java.io.File

class MainActivityMedia : AppCompatActivity() {
    lateinit var imageUri: Uri
    lateinit var outputImage: File
    private val mediaPlayer = MediaPlayer()

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
        // 通知
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
        // 拍照
        findViewById<Button>(R.id.takePhotoBtn).setOnClickListener {
            // 创建File对象，用于存储拍照后的图片
            outputImage = File(externalCacheDir, "output_image.jpg")
            if (outputImage.exists()) {
                outputImage.delete()
            }
            outputImage.createNewFile()
            imageUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                FileProvider.getUriForFile(this, "com.example.media.fileProvider", outputImage)
            } else {
                Uri.fromFile(outputImage)
            }
            // 启动相机程序
            takePictureLauncher.launch(imageUri)
        }
        // 拍照预览
        findViewById<Button>(R.id.takePhotoViewBtn).setOnClickListener {
            takePicturePreviewLauncher.launch(null)
        }
        // 从相册选取图片
        findViewById<Button>(R.id.fromAlbumBtn).setOnClickListener {
            createDocumentLauncher.launch(arrayOf("image/*"))
        }
        // 初始化音频播放器
        initMediaPlayer()
        findViewById<Button>(R.id.play).setOnClickListener {
            if (!mediaPlayer.isPlaying) {
                mediaPlayer.start() // 开始播放
            }
        }
        findViewById<Button>(R.id.pause).setOnClickListener {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause() // 暂停播放
            }
        }
        findViewById<Button>(R.id.stop).setOnClickListener {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.reset() // 停止播放
                initMediaPlayer()
            }
        }
    }

    // 拍照并将图片保存到给定的Uri地址，返回Boolean代表成败
    private val takePictureLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()) {
        if (it) {
            val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(imageUri))
            findViewById<ImageView>(R.id.imageView).setImageBitmap(rotateIfRequired(bitmap))
        }
    }

    // 拍照返回值为Bitmap
    private val takePicturePreviewLauncher = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) {
        it?.let {
            findViewById<ImageView>(R.id.imageView).setImageBitmap(it)
        }
    }

    // 打开文件选择器的回调
    private val createDocumentLauncher = registerForActivityResult(ActivityResultContracts.OpenDocument()) {
        it?.let {
            val bitmap = getBitmapFromUri(it)
            findViewById<ImageView>(R.id.imageView).setImageBitmap(bitmap)
        }
    }

    private fun rotateIfRequired(bitmap: Bitmap): Bitmap {
        val exif = ExifInterface(outputImage.path)
        return when (exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)) {
            ExifInterface.ORIENTATION_ROTATE_90 -> rotateBitMap(bitmap, 90)
            ExifInterface.ORIENTATION_ROTATE_180 -> rotateBitMap(bitmap, 180)
            ExifInterface.ORIENTATION_ROTATE_270 -> rotateBitMap(bitmap, 270)
            else -> bitmap
        }
    }

    private fun rotateBitMap(bitmap: Bitmap, degree: Int): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(degree.toFloat())
        val rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
        bitmap.recycle()
        return rotatedBitmap
    }

    private fun getBitmapFromUri(uri: Uri) = contentResolver
        .openFileDescriptor(uri, "r")?.use {
            BitmapFactory.decodeFileDescriptor(it.fileDescriptor)
        }

    private fun initMediaPlayer() {
        val assetManager = assets
        val fd = assetManager.openFd("sin.wav")
        mediaPlayer.setDataSource(fd.fileDescriptor, fd.startOffset, fd.length)
        mediaPlayer.prepare()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
        mediaPlayer.release()
    }
}