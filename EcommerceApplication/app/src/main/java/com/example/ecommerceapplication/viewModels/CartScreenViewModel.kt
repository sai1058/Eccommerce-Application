package com.example.ecommerceapplication.viewModels

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.example.ecommerceapplication.data.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CartScreenViewModel : ViewModel() {
    private lateinit var sharedPreferences: SharedPreferences
    val selectedProducts = mutableListOf<Product>()
    fun addToCart(product: Product) {
        val existingProduct = selectedProducts.find { it.id == product.id }
        if (existingProduct != null) {
            existingProduct.count++
            existingProduct.totalPrice = existingProduct.price * existingProduct.count

        } else {
            product.count = 1
            product.totalPrice = product.price * product.count
            selectedProducts.add(product)
        }
        saveCartToSharedPreferences()
    }
    fun clearCart() {
        selectedProducts.clear()
        saveCartToSharedPreferences()

    }
    fun removeItem(index: Int) {
        val product = selectedProducts[index]
        if (product.count > 1) {
            product.count--
            product.totalPrice = product.price * product.count
        } else {
            selectedProducts.removeAt(index)
        }
        saveCartToSharedPreferences()

    }
    fun initSharedPreferences(context: Context) {
        val sharedPreferences = context.getSharedPreferences("cart_preferences", Context.MODE_PRIVATE)
        val cartJson = sharedPreferences.getString("cart", null)
        val cartItemList: MutableList<Product>? = Gson().fromJson(cartJson, object : TypeToken<MutableList<Product>?>() {}.type)
        if (cartItemList != null) {
            selectedProducts.clear()
            selectedProducts.addAll(cartItemList)
        }
    }
    private fun saveCartToSharedPreferences() {
       // sharedPreferences = context.getSharedPreferences("cart_preferences", Context.MODE_PRIVATE)
        val cartJson = Gson().toJson(selectedProducts)
        sharedPreferences.edit().putString("cart", cartJson).apply()
    }
}
