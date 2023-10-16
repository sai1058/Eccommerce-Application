package com.example.exchangeratesapplication

import okhttp3.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RestClient {

    @GET("latest.json")
    suspend fun getLatestExchangeRates(@Query("app_id") app_id:String):ExchangeDataResponse
}