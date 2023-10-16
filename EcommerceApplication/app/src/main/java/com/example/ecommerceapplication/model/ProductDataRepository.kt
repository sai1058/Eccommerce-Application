package com.example.ecommerceapplication.model

import com.example.ecommerceapplication.data.Product
import com.example.ecommerceapplication.model.NetworkManager.restClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.Result

interface ProductDataRepository {
    suspend fun fetchProductData(): Result<List<Product>>
    suspend fun fetchProductDetails(id: Int): Result<Product>

}

class ProductDataRepositoryImpl : ProductDataRepository {
    override suspend fun fetchProductData(): Result<List<Product>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = restClient.getProducts()
                if (response.isSuccessful) {
                    val productList = response.body()
                    if (productList != null) {
                        Result.success(productList)
                    } else {
                        Result.failure(Exception("Empty response body"))
                    }
                } else {
                    Result.failure(Exception("Network request failed"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
    override suspend fun fetchProductDetails(id: Int): Result<Product> {
        return try {
            val response = restClient.getProductDetails(id)
            if (response.isSuccessful) {
                val product = response.body()
                if (product != null) {
                    Result.success(product)
                } else {
                    Result.failure(Exception("Empty response body"))
                }
            } else {
                Result.failure(Exception("HTTP Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}