package com.example.mynewsapplication

import junit.framework.TestCase.assertEquals
//import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

//@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class HomeViewModelTest {
    private lateinit var viewModel: HomeViewModel
    private lateinit var fakeRepository: FakeNewsRepository

    @Before
    fun setup() {
        fakeRepository = FakeNewsRepository()
        viewModel = HomeViewModel(fakeRepository)
        Dispatchers.setMain(Dispatchers.Unconfined)

    }
    @Test
    fun `fetchNewsData success`() = runBlocking{
        val mockResponse = NewsResponse()
        fakeRepository.setFakeResponse(mockResponse)

        viewModel.fetchNewsData("india")

        delay(100)

        assertEquals(mockResponse, viewModel.newsArticle.value)
        assertEquals(false, viewModel.errorLiveData.value)
    }

    private fun NewsResponse(): NewsResponse {
        val mockArticles = listOf(
            Article(
                "",
                "",
                "",
                "https://media.wired.com/photos/64f0efed922370ac6de903c5/191:100/w_1280,c_limit/IITs-Golden-Tech-Ticket-Dark-Side-Business-1593768989.jpg",
                "",
                ""
            ),
        )
        return NewsResponse(
            status = "ok",
            totalResults = 6,
            articles = mockArticles
        )
    }

    @Test
    fun `fetchNewsData failure`() {
        val errorMessage = "Test error message"
        val fakeError = Exception(errorMessage)
        fakeRepository.setFakeError(fakeError)

        viewModel.fetchNewsData("india")

        assertEquals(null, viewModel.newsArticle.value)
        assertEquals(false, viewModel.errorLiveData.value)
    }
    @After
    fun cleanup() {
        Dispatchers.resetMain()
    }
}

