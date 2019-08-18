package com.example.test

interface Savable {

    fun init(data: ByteArray)

    fun toBytes(): ByteArray

    fun describe(): String
}