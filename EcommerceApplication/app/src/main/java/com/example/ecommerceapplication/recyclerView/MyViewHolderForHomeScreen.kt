package com.example.ecommerceapplication.recyclerView

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceapplication.data.ItemOnClick
import com.example.ecommerceapplication.R
import com.example.ecommerceapplication.data.Product

class MyViewHolderForHomeScreen(itemView: View, private val itemOnClick: ItemOnClick) : RecyclerView.ViewHolder(itemView) {
    fun bind(productData: Product) {
        val imageView = itemView.findViewById<ImageView>(R.id.image)
        val textView = itemView.findViewById<TextView>(R.id.textViewForTitle)


        textView.text = productData.title
        Glide.with(itemView.context)
            .load(productData.image)
            .into(imageView)

        itemView.setOnClickListener {
            itemOnClick.itemClick(adapterPosition)
        }
    }
}