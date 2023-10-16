package com.example.mynewsapplication

//import okhttp3.Response
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RestClient {

    @GET("everything")
    suspend fun getLatestNews(@Query("apiKey") app_id:String, @Query("q") query:String): Response<NewsResponse>
}