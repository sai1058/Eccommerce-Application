package com.example.ecommerceapplication.viewModels

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.ecommerceapplication.data.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CartScreenViewModel : ViewModel() {
    val selectedProducts = mutableListOf<Product>()
    fun addToCart(product: Product) {
        val existingProduct = selectedProducts.find { it.id == product.id }
        if (existingProduct != null) {
            existingProduct.count++
            //existingProduct.totalPrice = existingProduct.price * existingProduct.count

        } else {
            product.count = 1
            //product.totalPrice = product.price * product.count
            selectedProducts.add(product)
        }
    }
    fun clearCart() {
        selectedProducts.clear()
    }
    fun removeItem(index: Int) {
        val product = selectedProducts[index]
        if (product.count > 1) {
            product.count--
            Log.i("Count is ", "${product.count}")
            product.totalPrice = product.price*product.count
        } else {
            selectedProducts.removeAt(index)
        }
    }
}
