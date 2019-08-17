package com.example.RecyclerView

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class ShowActivity : AppCompatActivity() {
    //初始化，有add，remove方法的集合
    var list=mutableListOf<String>()
    //初始化必须赋值，只读模式
    var data= listOf(
"我","成","功","了"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show)
        initUI()
    }

    @SuppressLint("WrongConstant")
    private fun initUI() {
        val mRecyclerView:RecyclerView = findViewById(R.id.rv_list)
        val layoutManager= LinearLayoutManager(this)
//        val layout=GridLayoutManager(this,2);
        mRecyclerView.layoutManager=layoutManager
        val adapter=  RvAdapter(data)
        mRecyclerView.adapter=adapter

    }
}
