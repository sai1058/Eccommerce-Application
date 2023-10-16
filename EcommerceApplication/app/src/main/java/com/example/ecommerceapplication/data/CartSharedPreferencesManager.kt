package com.example.ecommerceapplication.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CartSharedPreferencesManager(private val context: Context) {
    private val sharedPreferences = context.getSharedPreferences("CartSharedPreferences", Context.MODE_PRIVATE)

    fun saveCart(cartItems: List<Product>) {
        val gson = Gson()
        val json = gson.toJson(cartItems)
        sharedPreferences.edit().putString("cartItems", json).apply()
    }

    fun getCart(): List<Product> {
        val json = sharedPreferences.getString("cartItems", "")
        val gson = Gson()
        val type = object : TypeToken<List<Product>>() {}.type
        return gson.fromJson(json, type) ?: mutableListOf()
    }

}
