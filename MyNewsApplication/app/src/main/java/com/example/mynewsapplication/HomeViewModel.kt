package com.example.mynewsapplication


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HomeViewModel(): ViewModel() {
    var newsRepository: NewsRepository = NewsRepositoryImpl()
    val newsArticle = MutableLiveData<NewsResponse>()
    var errorLiveData = MutableLiveData(false)

    constructor(newsRepository: NewsRepository) : this() {
        this.newsRepository = newsRepository
    }

    fun fetchNewsData(query: String) {
        viewModelScope.launch {
            val dataResponse = newsRepository.fetchData(query)
            dataResponse.onSuccess {
                newsArticle.postValue(it)
            }
            dataResponse.onFailure {
                errorLiveData.postValue(true)
            }
        }
    }

}

