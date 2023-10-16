package com.example.ecommerceapplication.model

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkManager {
    private const val  BASEURL: String = "https://fakestoreapi.com/"
    var restClient: RestClient
    private val okHttpClient = OkHttpClient.Builder().build()
    init {
        restClient = Retrofit.Builder().client(okHttpClient).baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(RestClient::class.java)
    }
}