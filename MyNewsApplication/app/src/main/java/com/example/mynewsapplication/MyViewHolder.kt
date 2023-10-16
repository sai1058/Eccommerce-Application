package com.example.mynewsapplication

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MyViewHolder(itemView: View, private val itemOnClick: ItemOnClick) : RecyclerView.ViewHolder(itemView) {
    fun bind(newsArticle: Article) {
        val titleTextView =itemView.findViewById<TextView>(R.id.titleTextView)
        val descriptionTextView = itemView.findViewById<TextView>(R.id.descriptionTextView)
        val imageView = itemView.findViewById<ImageView>(R.id.imageView)

        titleTextView.text = newsArticle.title
        descriptionTextView.text = newsArticle.description

        // Load the image using Glide (make sure to import the Glide library)
        Glide.with(itemView.context)
            .load(newsArticle.urlToImage) // Replace with the actual image URL property in your Article class // Optional error image
            .into(imageView)

        itemView.setOnClickListener {
            itemOnClick.itemClick(adapterPosition)
        }

        // Set click listener for the item
    }
}


//    fun bind(newsArticle: Article) {
//        itemView.findViewById<TextView>(R.id.titleTextView).text = newsArticle.title
//        itemView.findViewById<TextView>(R.id.descriptionTextView).text = newsArticle.description
//        itemView.findViewById<TextView>(R.id.authorTextView).text = "Author: ${newsArticle.author}"
//
//        val imageView = itemView.findViewById<ImageView>(R.id.imageView)
//        Glide.with(itemView.context)
//            .load(newsArticle)
//            .into(imageView)
//    }