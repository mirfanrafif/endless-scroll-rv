package com.mirfanrafif.endlessrv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RvAdapter(val data: ArrayList<String>): RecyclerView.Adapter<RvAdapter.RvViewHolder>() {
    class RvViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val counter: TextView = itemView.findViewById(R.id.counter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvAdapter.RvViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        return RvViewHolder(view)
    }

    override fun onBindViewHolder(holder: RvAdapter.RvViewHolder, position: Int) {
        holder.counter.text = data[position]
    }

    override fun getItemCount(): Int {
        return data.size
    }
}