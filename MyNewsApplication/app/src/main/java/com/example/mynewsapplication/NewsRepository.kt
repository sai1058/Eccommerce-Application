package com.example.mynewsapplication

//import com.bumptech.glide.load.HttpException
import android.util.Log
import java.io.IOException
import retrofit2.HttpException
interface NewsRepository  {
    suspend fun fetchData(query: String): Result<NewsResponse>

}
class NewsRepositoryImpl : NewsRepository {
    override suspend fun fetchData(query: String): Result<NewsResponse> {
        return try {
            val response = NetworkManager.restClient.getLatestNews("14acf965ef4a4f9ba782c80ca223dc79", query)

            if (response.isSuccessful) {
                val newsResponse = response.body()
                if (newsResponse != null) {
                        Result.success(newsResponse)
                } else {
                    Result.failure(NullPointerException("Response body is null"))
                }
            } else {
                if (response.code() == 401) {
                    Result.failure(Exception("Unauthorized: Invalid API Key"))
                } else {
                    Result.failure(HttpException(response))
                }
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

