package com.example.ecommerceapplication.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapplication.R
import com.example.ecommerceapplication.data.ItemOnClick
import com.example.ecommerceapplication.data.Product

class CartScreenAdapter(private val productListItems: MutableList<Product>, private val itemClick: ItemOnClick) : RecyclerView.Adapter<CartScreenViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartScreenViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.checkout_list, parent, false)
        return CartScreenViewHolder(view, itemClick)
    }
    override fun onBindViewHolder(holder: CartScreenViewHolder, position: Int) {
        val productDataList = productListItems[position]
        holder.bind(productDataList)
        val product = productDataList.totalPrice
    }
    override fun getItemCount(): Int {
        return productListItems.size
    }
    fun getItem(index: Int): Product {
        return productListItems[index]
    }
    fun removeItem(index: Int){
        productListItems.removeAt(index)
        notifyItemRemoved(index)
        notifyDataSetChanged()
    }
}