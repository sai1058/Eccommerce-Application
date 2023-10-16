package com.example.exchangeratesapplication

import android.util.Log

interface CurrencyExchangeRepository  {
    suspend fun fetchData(): Result<ExchangeDataResponse>

}
class CurrencyExchangeRepositoryImpl : CurrencyExchangeRepository{
    override suspend fun fetchData(): Result<ExchangeDataResponse>{
        return try {
            val response =
                NetworkManager.restClient.getLatestExchangeRates("35ae095630094e17a6bad7d666e19454")
            Result.success(response)
        } catch (e: Exception){
            Result.failure(e)
        }
    }
}