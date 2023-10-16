package com.example.exchangeratesapplication

data class ExchangeDataResponse(
    val disclaimer: String?,
    val license: String?,
    val timestamp: Long?,
    val base: String?,
    val rates: Map<String, Any>?
)