package com.example.exchangeratesapplication

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkManager {
    private const val  BASEURL: String = "https://openexchangerates.org/api/"
    private const val APIKEY: String = "35ae095630094e17a6bad7d666e19454"
    var restClient: RestClient
    private val okHttpClient = OkHttpClient.Builder().build()
    init {
         restClient = Retrofit.Builder().client(okHttpClient).baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(RestClient::class.java)
    }
}