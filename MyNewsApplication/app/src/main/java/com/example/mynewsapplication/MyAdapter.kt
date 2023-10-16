package com.example.mynewsapplication

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val newsList: MutableList<Article>, private val itemOnClick: ItemOnClick) : RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(view,itemOnClick)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val newsArticle = newsList[position]
        holder.bind(newsArticle)
    }
    override fun getItemCount(): Int {
        return newsList.size
    }
    fun getItem(index: Int): Article {
        return newsList[index]
    }
    fun updateData(newData: List<Article>) {
        newsList.clear()
        newsList.addAll(newData)
        notifyDataSetChanged()
    }
}
