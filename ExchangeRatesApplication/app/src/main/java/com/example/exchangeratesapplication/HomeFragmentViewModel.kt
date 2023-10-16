package com.example.exchangeratesapplication

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HomeFragmentViewModel : ViewModel(){
    private val defaultCurrency = "USD"

    var dataMap = HashMap<String, Any>()
    var errorLiveData = MutableLiveData(false)
    private val currencyExchangeRepository: CurrencyExchangeRepository = CurrencyExchangeRepositoryImpl()
    val currencyRatesLiveData : MutableLiveData<List<Pair<String, Any>>> = MutableLiveData()
    fun fetchCurrencyData(){
        viewModelScope.launch {
            val dataResponse = currencyExchangeRepository.fetchData()
            dataResponse.onSuccess {
                it.rates?. let { dataMap.putAll(it) }
                currencyRatesLiveData.postValue(dataMap.toList())
                Log.i("Fetched data is", "$dataMap")
            }
            dataResponse.onFailure {
                errorLiveData.postValue(true)
                Log.i("Fetched data is not available", "$it")
            }
        }
    }
    fun convertRates(amount: Double): MutableList<Pair<String, Double>> {
        val convertedRates = mutableListOf<Pair<String, Double>>()

        val usdRate = dataMap[defaultCurrency] as? Double ?: 1.0

        for ((currency, rate) in dataMap) {
            val convertedAmount = amount * (rate as Double) / usdRate
            convertedRates.add(Pair(currency, convertedAmount))
        }
        return convertedRates
    }
}