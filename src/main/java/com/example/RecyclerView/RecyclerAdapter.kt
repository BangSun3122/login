package com.example.RecyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class RecyclerAdapter(private val context: Context, private val data: List<String>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val HEADER = 0
    private val NORMAL = 1
    private val FOOTER = 2

    override fun getItemCount(): Int = data.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = when (viewType) {
        HEADER -> {
            HeaderViewHolder(LayoutInflater.from(context).
                inflate(R.layout.item_header, parent, false))
        }

        FOOTER -> {
            FooterViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.item_footer, parent, false))
        }

        else -> {
            RecyclerViewHolder(
                LayoutInflater.from(context)
                .inflate(R.layout.item_recycler, parent, false))
        }

    }

    /**
     * 利用kotlin中的when代替Java中的if/else
     * 返回不同的item类型
     */
    override fun getItemViewType(position: Int): Int = when (position) {
        0 -> {
            HEADER
        }
        itemCount - 1 -> {
            FOOTER
        }
        else -> {
            NORMAL
        }
    }

    /**
     * 根据不同的item类型绑定不同的数据
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            HEADER -> {
                if (holder is HeaderViewHolder) {
                    holder.tv.text = "I am Header"
                    holder.tv.setOnClickListener {
                        Toast.makeText(context, "I am Header", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            FOOTER -> {
                if (holder is FooterViewHolder) {
                    holder.tv.text = "I am Footer"
                }
            }
            else -> {
                if (holder is RecyclerViewHolder) {
                    holder.textView.text = data[position]
                    holder.textView.setOnClickListener {
                        Toast.makeText(context, "click", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    inner class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textView: TextView = view.findViewById(R.id.item_tv)
    }

    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tv: TextView = view.findViewById(R.id.tv_header)
    }

    inner class FooterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tv: TextView = view.findViewById(R.id.tv_footer)
    }
    inner class FooterViewHolderTwo : RecyclerView.ViewHolder {
        constructor(view: View) : super(view) {
            tv = view.findViewById(R.id.tv_footer)
            tv.setOnClickListener {
                Toast.makeText(context, "I am Footer", Toast.LENGTH_SHORT).show()
            }
        }

        var tv: TextView
    }
    interface MyInterface {
        fun test(position: Int)
    }
    var myInterface: MyInterface? = null
    fun setListener(myInterface: MyInterface?) {
        this.myInterface = myInterface
    }
}