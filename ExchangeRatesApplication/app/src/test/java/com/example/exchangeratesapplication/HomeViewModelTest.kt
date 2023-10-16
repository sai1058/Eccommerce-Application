package com.example.exchangeratesapplication

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class HomeViewModelTest {
    var homeViewModel: HomeFragmentViewModel = HomeFragmentViewModel()

    @Before
    fun setup() {
        homeViewModel = HomeFragmentViewModel()

        homeViewModel.dataMap["USD"] = 3.0
        homeViewModel.dataMap["EUR"] = 2.55
        homeViewModel.dataMap["GBP"] = 2.19
    }
    @Test
    fun convertRates_withAmountZero_shouldReturnZeroForAllCurrencies() {

        val amount = 0.0
        val convertedRates = homeViewModel.convertRates(amount)

        convertedRates.forEach { (_, rate) ->
            assertEquals(0.0, rate, 0.001)
        }
    }
    @Test
    fun convertRates_withAmountOneUSD_shouldReturnSameValuesAsDataMap() {
        val amount = 3.0
        val convertedRates = homeViewModel.convertRates(amount)

        convertedRates.find { it.first == "USD" }?.second?.let { assertEquals(3.0, it, 0.001) }
        convertedRates.find { it.first == "EUR" }?.second?.let { assertEquals(2.55, it, 0.001) }
        convertedRates.find { it.first == "GBP" }?.second?.let { assertEquals(2.19, it, 0.001) }
    }
}