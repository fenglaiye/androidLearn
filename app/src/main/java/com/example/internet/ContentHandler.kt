package com.example.internet

import android.util.Log
import com.example.androidLearn.Global
import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler
import java.lang.StringBuilder

class ContentHandler : DefaultHandler() {
    private var nodeName = ""
    private lateinit var id: StringBuilder
    private lateinit var name: StringBuilder
    private lateinit var version: StringBuilder

    override fun startDocument() {
        id = StringBuilder()
        name = StringBuilder()
        version = StringBuilder()
    }

    override fun startElement(
        uri: String,
        localName: String,
        qName: String,
        attributes: Attributes
    ) {
        // 记录当前节点名
        nodeName = localName
        Log.d(Global.TAG, "uri- $uri, localName- $localName, qName- $qName, attributes- $attributes")
    }

    override fun characters(ch: CharArray, start: Int, length: Int) {
        // 根据当前节点名判断将内容添加到哪一个StringBuilder对象中
        when (nodeName) {
            "id" -> id.append(ch, start, length)
            "name" -> name.append(ch, start, length)
            "version" -> version.append(ch, start, length)
        }
    }

    override fun endElement(uri: String, localName: String, qName: String) {
        if ("app" == localName) {
            Log.d(Global.TAG, "id is ${id.toString().trim()}")
            Log.d(Global.TAG, "name is ${name.toString().trim()}")
            Log.d(Global.TAG, "version is ${version.toString().trim()}")
            // 最后要将StringBuilder清空
            id.setLength(0)
            name.setLength(0)
            version.setLength(0)
        }
    }

    override fun endDocument() {}
}