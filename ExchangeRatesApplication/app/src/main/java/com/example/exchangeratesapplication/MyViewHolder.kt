package com.example.exchangeratesapplication

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyViewHolder(itemView : View, itemClick: ItemOnClick) : RecyclerView.ViewHolder(itemView) {
    val textView: TextView = itemView.findViewById(R.id.textView)

    init {
        textView.setOnClickListener {
            itemClick.itemClick(adapterPosition)
        }
    }
}