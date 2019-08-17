package com.example.mvplogin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.logintest.LoginAct
import com.example.mvplogin.view.act.LoginActivity
import com.example.myapplication.R

class MainActivity: AppCompatActivity() {

    private var EButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.finalact)
        init()
    }

    private fun init() {
        EButton = findViewById<View>(R.id.button) as Button

        EButton!!.setOnClickListener {
            val intent = Intent(this@MainActivity, LoginAct::class.java)
            startActivity(intent)
        }
    }
}