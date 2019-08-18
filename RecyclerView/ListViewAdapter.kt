package com.example.RecyclerView

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.myapplication.R

class ListViewAdapter (var date : List<String>,var context: Context) : BaseAdapter() {

    override fun getItem(p0: Int): Any {
        return date.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return date.size
    }

    override fun getView(position: Int, convertView: View?, p2: ViewGroup?): View {
        var viewHolder:ViewHolder
        var view: View
        if (convertView == null) {
            view = View.inflate(context,R.layout.item_layout, null);
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        val item = getItem(position)
        if (item is String) {
            viewHolder.tv.text = item
            viewHolder.tV_name.text = item

        }
        return view
    }


    class ViewHolder(viewItem: View) {

        var tv: TextView = viewItem.findViewById(R.id.tv_content)
        var tV_name: TextView = viewItem.findViewById(R.id.tv_name)

    }
}