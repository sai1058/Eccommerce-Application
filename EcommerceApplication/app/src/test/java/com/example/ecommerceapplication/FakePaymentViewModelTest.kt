package com.example.ecommerceapplication
import com.example.ecommerceapplication.viewModels.FakePaymentViewModel

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class FakePaymentViewModelTest {
    private lateinit var fakePaymentViewModel: FakePaymentViewModel

    @Before
    fun setup() {
        fakePaymentViewModel = FakePaymentViewModel()
    }

    @Test
    fun testIsValid_validInput() {
        val cardNumber = "1234567890123456"
        val expiryDate = "1223"
        val cvvNumber = "123"
        val result = fakePaymentViewModel.isValid(cardNumber, expiryDate, cvvNumber)
        assertTrue(result)
    }

    @Test
    fun testIsValid_invalidInput() {
        // Invalid card number length
        val cardNumber = "123456789012345" // 15 characters
        val expiryDate = "1223"
        val cvvNumber = "123"
        val result = fakePaymentViewModel.isValid(cardNumber, expiryDate, cvvNumber)
        assertFalse(result)
    }

    @Test
    fun testIsCardNumberIsValid_validInput() {
        val cardNumber = "1234567890123456"
        val result = fakePaymentViewModel.isCardNumberIsValid(cardNumber)
        assertTrue(result)
    }

    @Test
    fun testIsCardNumberIsValid_invalidInput() {
        // Invalid card number (contains non-digits)
        val cardNumber = "1234-5678-9012-3456"
        val result = fakePaymentViewModel.isCardNumberIsValid(cardNumber)
        assertFalse(result)
    }

    @Test
    fun testIsExpiryDateIsValid_validInput() {
        val expiryDate = "1223"
        val result = fakePaymentViewModel.isExpiryDateIsValid(expiryDate)
        assertFalse(result)
    }

    @Test
    fun testIsExpiryDateIsValid_invalidInput() {
        // Invalid expiry date (contains non-digits)
        val expiryDate = "12523"
        val result = fakePaymentViewModel.isExpiryDateIsValid(expiryDate)
        assertFalse(result)
    }

    @Test
    fun testIsCvvNumberIsValid_validInput() {
        val cvvNumber = "123"
        val result = fakePaymentViewModel.isCvvNumberIsValid(cvvNumber)
        assertFalse(result)
    }

    @Test
    fun testIsCvvNumberIsValid_invalidInput() {
        // Invalid CVV number (contains non-digits)
        val cvvNumber = "1A3"
        val result = fakePaymentViewModel.isCvvNumberIsValid(cvvNumber)
        assertFalse(result)
    }
}
