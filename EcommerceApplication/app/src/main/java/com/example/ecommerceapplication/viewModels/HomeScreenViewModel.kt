package com.example.ecommerceapplication.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapplication.data.Product
import com.example.ecommerceapplication.model.ProductDataRepository
import com.example.ecommerceapplication.model.ProductDataRepositoryImpl
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {
    private var productDataRepository :ProductDataRepository = ProductDataRepositoryImpl()
    val productDetails = MutableLiveData<List<Product>>()
    val productDetailsWithId = MutableLiveData<List<Product>>()
    private var errorLiveData = MutableLiveData(false)
    fun fetchProduct() {
        viewModelScope.launch {
            val dataResponse = productDataRepository.fetchProductData()
            dataResponse.onSuccess {
                productDetails.postValue(it)
            }
            dataResponse.onFailure {
                errorLiveData.postValue(true)
            }
        }
    }
    fun fetchProductWithId(id: Int){
        viewModelScope.launch {
            val productResponse = productDataRepository.fetchProductDetails(id)
            productResponse.onSuccess{
                productDetailsWithId.postValue(listOf(it))
            }

        }
    }

}