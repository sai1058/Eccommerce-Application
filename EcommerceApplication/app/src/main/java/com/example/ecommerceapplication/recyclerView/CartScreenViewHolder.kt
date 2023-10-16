package com.example.ecommerceapplication.recyclerView

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceapplication.R
import com.example.ecommerceapplication.data.ItemOnClick
import com.example.ecommerceapplication.data.Product
import com.example.ecommerceapplication.viewModels.CartScreenViewModel

class CartScreenViewHolder(itemView: View, itemOnClick: ItemOnClick) :
    RecyclerView.ViewHolder(itemView) {
    val count = itemView.findViewById<TextView>(R.id.count)
    val pricing = itemView.findViewById<TextView>(R.id.price)
    val imageView = itemView.findViewById<ImageView>(R.id.imageViewForCart)
    val textView = itemView.findViewById<TextView>(R.id.descriptionTextViewForCart)

    init {
        itemView.findViewById<Button>(R.id.btnRemove).setOnClickListener {
            itemOnClick.itemClick(adapterPosition)
        }
    }
    fun bind(productDataList: Product) {
        pricing.text = "Cost: " + productDataList.price * productDataList.count + "$"
        textView.text = productDataList.description
        count.text = "Count: " + productDataList.count
        Glide.with(itemView.context)
            .load(productDataList.image)
            .into(imageView)
    }
}