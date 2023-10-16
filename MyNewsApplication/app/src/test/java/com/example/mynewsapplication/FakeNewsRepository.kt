package com.example.mynewsapplication

class FakeNewsRepository : NewsRepository {
    private var fakeResponse: NewsResponse? = null
    private var fakeError: Exception? = null

    fun setFakeResponse(response: NewsResponse) {
        fakeResponse = response
    }

    fun setFakeError(error: Exception) {
        fakeError = error
    }

    override suspend fun fetchData(query: String): Result<NewsResponse> {
        return if (fakeResponse != null) {
            Result.success(fakeResponse!!)
        } else if (fakeError != null) {
            Result.failure(fakeError!!)
        } else {
            Result.failure(Exception("No fake response or error set"))
        }
    }
}
