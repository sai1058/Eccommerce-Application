package com.example.ecommerceapplication.viewModels

import androidx.lifecycle.ViewModel

class FakePaymentViewModel : ViewModel() {
    fun isValid(cardNumber : String, expiryDate:String, cvvNumber: String):Boolean{
        return cardNumber.length == 16 && expiryDate.length == 4 && cvvNumber.length == 3
    }
    fun isCardNumberIsValid(cardNumber: String):Boolean{
        return cardNumber.matches(Regex("\\d{16}$"))
    }
    fun isExpiryDateIsValid(expiryDate: String):Boolean{
        return expiryDate.matches(Regex("\\d{16}$"))
    }
    fun isCvvNumberIsValid(cvvNumber: String):Boolean{
        return cvvNumber.matches(Regex("\\d{16}$"))
    }

}