package com.example.test

import android.annotation.SuppressLint
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.example.myapplication.R

@SuppressLint("Registered")
open class MenuBaseActivity : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent()
        when (item.itemId) {
            R.id.NormalActivity -> intent.setClass(this, NormalActivity::class.java)
            R.id.MoreApisPlayground -> intent.setClass(this, MoreApisPlayground::class.java)
            R.id.CommunicateWithBinderActivity -> intent.setClass(this, CommunicateWithBinderActivity::class.java)
            else -> return false
        }
        startActivity(intent)
        this.finish()
        return true
    }
}