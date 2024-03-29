package com.example.test

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.SystemClock.currentThreadTimeMillis
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.VisibleForTesting
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import me.drakeet.multitype.ItemViewBinder
import me.drakeet.multitype.MultiTypeAdapter

@SuppressLint("SetTextI18n")
class MoreApisPlayground : MenuBaseActivity() {

    private lateinit var terminal: TextView
    private lateinit var recyclerView: RecyclerView

    @VisibleForTesting
    lateinit var adapter: MultiTypeAdapter
    @VisibleForTesting
    lateinit var items: MutableList<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_apis_playground)
        recyclerView = findViewById(R.id.list)
        terminal = findViewById(R.id.terminal)
        terminal.text = TERMINAL_DEFAULT_TEXT

        items = ArrayList()
        adapter = MultiTypeAdapter()

        adapter.register(ObservableTextItemViewBinder())
        recyclerView.adapter = adapter

        for (i in 0..199) {
            items.add(TextItem(i.toString()))
        }
        adapter.items = items
        adapter.notifyDataSetChanged()
    }

    fun onAdd(view: View) {
        val bottom = items.size - 1
        items.add(TextItem(currentThreadTimeMillis().toString()))
        adapter.notifyItemInserted(bottom + 1)
        recyclerView.scrollToPosition(bottom + 1)
    }

    fun onRemove(view: View) {
        val bottom = items.size - 1
        recyclerView.scrollToPosition(bottom)
        items.removeAt(bottom)
        adapter.notifyItemRemoved(bottom)
    }

    fun onClear(view: View) {
        items.clear()
        adapter.notifyDataSetChanged()
    }

    private inner class ObservableTextItemViewBinder : ItemViewBinder<TextItem, ObservableTextItemViewBinder.TextHolder>() {

        private var buffer = 0

        inner class TextHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val text: TextView = itemView.findViewById(R.id.text)
        }

        override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): TextHolder {
            val root = inflater.inflate(R.layout.item_text, parent, false)
            return TextHolder(root)
        }

        override fun onBindViewHolder(holder: TextHolder, item: TextItem) {
            holder.text.text = "observable item(" + item.text + ")"
        }

        override fun onViewRecycled(holder: TextHolder) {
            appendTerminalLine("onViewRecycled: " + holder.text.text)
        }

        override fun onFailedToRecycleView(holder: TextHolder): Boolean {
            appendTerminalLine("onFailedToRecycleView: " + holder.text.text)
            return true
        }

        override fun onViewAttachedToWindow(holder: TextHolder) {
            appendTerminalLine("onViewAttachedToWindow: " + holder.text.text)
        }

        override fun onViewDetachedFromWindow(holder: TextHolder) {
            appendTerminalLine("onViewDetachedFromWindow: " + holder.text.text)
        }

        private fun appendTerminalLine(line: String) {
            if (buffer == 5) {
                terminal.text = TERMINAL_DEFAULT_TEXT
                buffer = 0
            }
            terminal.append("\n" + line)
            buffer++
        }
    }

    companion object {

        private const val TERMINAL_DEFAULT_TEXT = "ObservableTextItemViewBinder: "
    }
}