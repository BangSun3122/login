package com.example.test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import me.drakeet.multitype.ItemViewBinder

class ImageItemViewBinder : ItemViewBinder<ImageItem, ImageItemViewBinder.ImageHolder>() {

    inner class ImageHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.image)

    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ImageHolder {
        return ImageHolder(inflater.inflate(R.layout.item_image, parent, false))
    }

    override fun onBindViewHolder(holder: ImageHolder, item: ImageItem) {
        holder.image.setImageResource(item.resId)
    }
}