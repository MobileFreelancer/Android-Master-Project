package com.example.demoprojectandroid.Complexui

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demoprojectandroid.databinding.ItemMainBinding
import com.example.demoprojectandroid.databinding.ItemSportChecklistBinding

class InnerAdapter(
        private val activity: Activity,
        private val innerList: List<String>) : RecyclerView.Adapter<InnerAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemSportChecklistBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSportChecklistBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
                holder.binding.ckList.setText(innerList.get(position).toString())
    }


    override fun getItemCount(): Int = innerList.size

}