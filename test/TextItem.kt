package com.example.test


import com.google.gson.Gson
import java.nio.charset.Charset

class TextItem : Savable {

    lateinit var text: String

    constructor(text: String) {
        this.text = text
    }

    constructor(data: ByteArray) {
        init(data)
    }

    override fun init(data: ByteArray) {
        val json = String(data, UTF_8)
        this.text = Gson().fromJson(json, TextItem::class.java).text
    }

    override fun toBytes(): ByteArray {
        return Gson().toJson(this).toByteArray(UTF_8)
    }

    override fun describe(): String {
        return "Text"
    }

    companion object {
        private val UTF_8 = Charset.forName("UTF-8")
    }
}