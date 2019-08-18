package com.example.test

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import me.drakeet.multitype.MultiTypeAdapter

class NormalActivity : MenuBaseActivity() {

    private lateinit var adapter: MultiTypeAdapter
    private lateinit var items: MutableList<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        val recyclerView = findViewById<RecyclerView>(R.id.list)

        adapter = MultiTypeAdapter()
        adapter.register(TextItemViewBinder())
        adapter.register(ImageItemViewBinder())
        adapter.register(RichItemViewBinder())
        recyclerView.adapter = adapter

        val textItem = TextItem("难道这个是标题")
        val imageItem = ImageItem(R.mipmap.img_1)
        val richItem = RichItem("这个应该是内容", R.drawable.img_11)

        items = ArrayList()
        for (i in 0..19) {
            items.add(textItem)
            items.add(imageItem)
            items.add(richItem)
        }
        adapter.items = items
        adapter.notifyDataSetChanged()
    }
}