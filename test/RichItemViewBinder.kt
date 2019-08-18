package com.example.test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import me.drakeet.multitype.ItemViewBinder

class RichItemViewBinder : ItemViewBinder<RichItem, RichItemViewBinder.RichHolder>() {

    class RichHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text: TextView = itemView.findViewById(R.id.text)
        val image: ImageView = itemView.findViewById(R.id.image)
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): RichHolder {
        return RichHolder(inflater.inflate(R.layout.item_rich, parent, false))
    }

    override fun onBindViewHolder(holder: RichHolder, item: RichItem) {
        holder.text.text = item.text
        holder.image.setImageResource(item.imageResId)
    }
}