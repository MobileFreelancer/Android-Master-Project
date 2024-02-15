package com.example.demoprojectandroid.CommonUi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.demoprojectandroid.R

class DrawerAdapter(context: Context, private val items: List<String>) : BaseAdapter() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int = items.size

    override fun getItem(position: Int): Any = items[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val holder: ViewHolder

        if (convertView == null) {
            view = inflater.inflate(R.layout.item_navigation_menu, parent, false)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }

        holder.title.text = items[position]

        return view
    }

    private class ViewHolder(view: View) {
        val title: TextView = view.findViewById(R.id.tvName)
    }
}
