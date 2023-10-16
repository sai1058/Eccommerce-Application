package com.example.ecommerceapplication.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapplication.data.ItemOnClick
import com.example.ecommerceapplication.R
import com.example.ecommerceapplication.data.Product

class MyAdapter(private val productList: MutableList<Product>, private val itemOnClick: ItemOnClick) : RecyclerView.Adapter<MyViewHolderForHomeScreen>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderForHomeScreen {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_items, parent, false)
        return MyViewHolderForHomeScreen(view,itemOnClick)
    }

    override fun onBindViewHolder(holder: MyViewHolderForHomeScreen, position: Int) {
        val productData = productList[position]
        holder.bind(productData)
    }
    override fun getItemCount(): Int {
        return productList.size
    }
    fun getItem(index: Int): Product {
        return productList[index]
    }
    fun updateData(newData: List<Product>) {
        productList.clear()
        productList.addAll(newData)
        notifyDataSetChanged()
    }
}