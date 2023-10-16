package com.example.ecommerceapplication.data

data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: Rating,
    var count:Int = 1,
    var totalPrice : Double = price
)

data class Rating(
    val rate: Double,
    val count: Int
)

