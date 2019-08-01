package com.carllewis14.revoluttest.usecases

import com.carllewis14.revoluttest.network.RevolutRatesApi
import com.carllewis14.revoluttest.data.model.names.CurrencyNamesResponse
import com.carllewis14.revoluttest.data.model.rates.CurrencyRatesResponse
import com.carllewis14.revoluttest.util.concurrency.scheduler.SchedulerProvider
import io.reactivex.Observable

class CurrenciesUseCaseImpl(private val revolutRatesApi: RevolutRatesApi, private val schedulerProvider: SchedulerProvider):
    CurrenciesUseCase {

    override fun getNames(): Observable<CurrencyNamesResponse> {
        return revolutRatesApi.getCurrencyNames()
            .subscribeOn(schedulerProvider.io)
    }

    override fun getRates(base: String): Observable<CurrencyRatesResponse> {
        return revolutRatesApi.getCurrencyRates(base)
            .subscribeOn(schedulerProvider.io)
    }

}