package com.example.mynewsapplication
data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article> // Assuming Article is another data class
)

data class Article(
    val author: String?,
    val title: String?,
    val description: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?
)


data class Source(
    val id: String?,
    val name: String
)
