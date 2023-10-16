package com.example.exchangeratesapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val currencyData: List<Pair<String, Any>>, private val itemClick: ItemOnClick) : RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return MyViewHolder(view,itemClick)
    }

    override fun getItemCount(): Int {
       return currencyData.size
    }

    override fun onBindViewHolder(holder:MyViewHolder, position: Int) {
        val(currency, rate ) = currencyData[position]
        holder.textView.text = "$currency : $rate"
    }


}