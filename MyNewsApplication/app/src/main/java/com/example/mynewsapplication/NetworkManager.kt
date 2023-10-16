package com.example.mynewsapplication

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkManager {
    private const val  BASEURL: String = "https://newsapi.org/v2/"
    private const val APIKEY: String = "14acf965ef4a4f9ba782c80ca223dc79"
    var restClient: RestClient
    private val okHttpClient = OkHttpClient.Builder().build()
    init {
         restClient = Retrofit.Builder().client(okHttpClient).baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(RestClient::class.java)
    }
}