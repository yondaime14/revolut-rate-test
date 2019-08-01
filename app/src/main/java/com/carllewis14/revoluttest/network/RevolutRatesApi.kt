package com.carllewis14.revoluttest.network

import com.carllewis14.revoluttest.data.model.names.CurrencyNamesResponse
import com.carllewis14.revoluttest.data.model.rates.CurrencyRatesResponse
import com.carllewis14.revoluttest.util.Constants
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RevolutRatesApi {

    @GET("latest")
    fun getCurrencyRates(@Query("base") base: String): Observable<CurrencyRatesResponse>

    @GET(Constants.COUNTRY_CODES_URL)
    fun getCurrencyNames(): Observable<CurrencyNamesResponse>

}