package com.carllewis14.revoluttest.usecases

import com.carllewis14.revoluttest.data.model.names.CurrencyNamesResponse
import com.carllewis14.revoluttest.data.model.rates.CurrencyRatesResponse
import io.reactivex.Observable

interface CurrenciesUseCase {

    fun getNames(): Observable<CurrencyNamesResponse>
    fun getRates(base: String): Observable<CurrencyRatesResponse>

}