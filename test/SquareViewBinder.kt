package com.example.test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import me.drakeet.multitype.ItemViewBinder

class SquareViewBinder(val selectedSet: MutableSet<Int>) : ItemViewBinder<Square, SquareViewBinder.ViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_square, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, item: Square) {
        holder.square = item
        holder.squareView.text = item.number.toString()
        holder.squareView.isSelected = item.isSelected
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val squareView: TextView = itemView.findViewById(R.id.square)
        lateinit var square: Square

        init {
            itemView.setOnClickListener {
                square.apply {
                    squareView.isSelected = !isSelected
                    this.isSelected = !isSelected
                }
                if (square.isSelected) {
                    selectedSet.add(square.number)
                } else {
                    selectedSet.remove(square.number)
                }
            }
        }
    }
}