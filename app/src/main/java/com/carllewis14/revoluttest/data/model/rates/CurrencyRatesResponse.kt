package com.carllewis14.revoluttest.data.model.rates

data class CurrencyRatesResponse(
    val base: String? = null,
    val date: String? = null,
    val currencies: MutableList<Currency>? = null
)