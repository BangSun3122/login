package com.example.test

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import me.drakeet.multitype.MultiTypeAdapter

class CommunicateWithBinderActivity : MenuBaseActivity() {

    private val aFieldValue = "aFieldValue of SimpleActivity"
    private var adapter: MultiTypeAdapter = MultiTypeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        val recyclerView = findViewById<RecyclerView>(R.id.list)

        val items = ArrayList<Any>()
        adapter.register(TextItemWithOutsizeDataViewBinder(aFieldValue))
        recyclerView.adapter = adapter

        for (i in 0..19) {
            items.add(TextItem(i.toString()))
        }
        adapter.items = items
        adapter.notifyDataSetChanged()
    }
}