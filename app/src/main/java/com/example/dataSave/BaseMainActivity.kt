package com.example.dataSave

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.androidLearn.Global
import com.example.androidLearn.databinding.ActivityMainBaseBinding
import java.lang.Exception
import java.lang.NullPointerException

private lateinit var binding: ActivityMainBaseBinding

class BaseMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // 创建数据库
        val dbHelper = MyDatabaseHelper(this, "BookStore.db", 4)
        binding.createDatabase.setOnClickListener {
            dbHelper.writableDatabase
        }
        // 添加数据
        binding.addData.setOnClickListener {
            val db = dbHelper.writableDatabase
            val value1 = ContentValues().apply {
                // 开始组装第一条数据
                put("name", "Android 大法")
                put("author", "WC")
                put("pages", 4542)
                put("price", 96.96)
            }
            // 插入第一条数据
            db.insert("Book", null, value1)
            val value2 = ContentValues().apply {
                put("name", "Xin Liu")
                put("author", "WC")
                put("pages", 200)
                put("price", 59.95)
            }
            db.insert("Book", null, value2)
        }
        // 更新数据
        binding.updateData.setOnClickListener {
            val db = dbHelper.writableDatabase
            val values = ContentValues()
            values.put("price", 9.99)
            db.update("Book", values, "name = ?", arrayOf("The Da Vinci Code"))
        }
        // 删除数据
        binding.deleteData.setOnClickListener {
            val db = dbHelper.writableDatabase
            db.delete("Book", "id > ?", arrayOf("2"))
        }
        // 查询数据
        binding.queryData.setOnClickListener {
            val db = dbHelper.writableDatabase
            // 查询所有
            val cursor = db.query("Book", null, null, null, null, null, null, null)
            if (cursor.moveToFirst()) {
                do {
                    // 遍历cursor对象，取出数据打印
                    cursor.apply {
                        val name = getString(getColumnIndex("name"))
                        val author = getString(getColumnIndex("author"))
                        val pages = getInt(getColumnIndex("pages"))
                        val price = getDouble(getColumnIndex("price"))
                        Log.d(Global.TAG,"name is $name, $author, $pages, $price")
                    }
                } while (cursor.moveToNext())
            }
            cursor.close()
        }
        // 使用事务替换数据
        binding.replaceData.setOnClickListener {
            val db = dbHelper.writableDatabase
            // 开启事务
            db.beginTransaction()
            try {
                db.delete("Book", null, null)
                val values = ContentValues().apply {
                    put("name", "那年夏天")
                    put("author", "小孩子")
                    put("pages", 520)
                    put("price", 52.0)
                }
                db.insert("Book", null, values)
                db.setTransactionSuccessful() // 事务已经执行成功
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                db.endTransaction() // 结束事务
            }
        }
    }
}
