package com.carllewis14.revoluttest.viewmodel

import android.arch.lifecycle.ViewModel
import com.carllewis14.revoluttest.base.Injector
import com.carllewis14.revoluttest.data.model.names.CurrencyNamesResponse
import com.carllewis14.revoluttest.data.model.rates.Currency
import com.carllewis14.revoluttest.data.model.rates.CurrencyRatesResponse
import com.carllewis14.revoluttest.usecases.CurrenciesUseCase
import com.carllewis14.revoluttest.util.concurrency.scheduler.SchedulerProvider
import com.carllewis14.revoluttest.util.log.LogUtil
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class RevolutRatesViewModel : ViewModel() {

    @Inject
    lateinit var mCurrenciesUseCase: CurrenciesUseCase

    @Inject
    lateinit var mSchedulerProvider: SchedulerProvider

    @Inject
    lateinit var mLogUtil: LogUtil

    private var mCurrenciesListDisposable: Disposable? = null

    private var mBaseCurrencySubject = BehaviorSubject.create<Currency>()
    private var mCurrenciesSubject = BehaviorSubject.create<List<Currency>>()
    private var mShowProgress = BehaviorSubject.create<Boolean>()

    private var mPreviousBaseCurrencyCodes = ArrayList<String>()
    private var mDefaultCurrency = Currency("EUR", "", 100.0)

    init {
        Injector.appComponent.inject(this)
        initBaseCurrencySubject()
    }

    private fun initBaseCurrencySubject() {
        mBaseCurrencySubject
            .doOnNext {
                mPreviousBaseCurrencyCodes.remove(it.code)
                mPreviousBaseCurrencyCodes.add(0, it.code!!)
                mShowProgress.onNext(true)
            }
            .subscribe()

        mBaseCurrencySubject.onNext(mDefaultCurrency)
    }

    fun onStart() {
        mCurrenciesListDisposable = getCurrencyListObservable()
            .subscribe(
                { currencies ->
                    mCurrenciesSubject.onNext(currencies)
                    mShowProgress.onNext(false)
                },
                { error -> mLogUtil.log("Error happened") }
            )
    }

    fun onStop() {
        mCurrenciesListDisposable?.dispose()
    }

    private fun getCurrencyListObservable(): Observable<ArrayList<Currency>> {

        val currencyNamesObservable = getNamesObservable()
        val currencyRatesObservable = getRatesObservable()

        val currencyListObservable =
            Observable.combineLatest<CurrencyNamesResponse, CurrencyRatesResponse, ArrayList<Currency>>(
                currencyNamesObservable,
                currencyRatesObservable,
                BiFunction { namesResponse, ratesResponse ->
                    var currencies = getUpdatedCurrenciesList(namesResponse, ratesResponse)
                    currencies
                })
                .observeOn(mSchedulerProvider.ui)

        return currencyListObservable

    }

    private fun getNamesObservable(): Observable<CurrencyNamesResponse> {
        val currencyNamesObservable = mCurrenciesUseCase.getNames()
        return currencyNamesObservable
    }

    private fun getRatesObservable(): Observable<CurrencyRatesResponse>? {
        val currencyRatesObservable = Observable.combineLatest<Currency, Long, String>(
            mBaseCurrencySubject,
            Observable.interval(0, 1, TimeUnit.SECONDS),
            BiFunction { currency, long -> currency.code!! })
            .flatMap { mCurrenciesUseCase.getRates(getBaseCurrency().code!!) }
        return currencyRatesObservable
    }

    private fun getBaseCurrency(): Currency {
        if (mBaseCurrencySubject.value != null) return mBaseCurrencySubject.value!!
        else return mDefaultCurrency
    }

    private fun getUpdatedCurrenciesList(
        namesResponse: CurrencyNamesResponse,
        ratesResponse: CurrencyRatesResponse
    ): ArrayList<Currency> {

        val currencies = ArrayList<Currency>()

        val baseCurrency = getBaseCurrency()
        baseCurrency.name = namesResponse.namesMap[baseCurrency.code]
        currencies.add(baseCurrency)

        for (currency in ratesResponse.currencies!!) {
            currency.name = namesResponse.namesMap[currency.code]
            currency.rate = currency.rate * baseCurrency.rate
        }

        for (code in mPreviousBaseCurrencyCodes) {
            val currency = ratesResponse.currencies.find { it.code == code }
            if (currency != null) {
                currencies.add(currency)
                ratesResponse.currencies.remove(currency)
            }
        }

        currencies.addAll(ratesResponse.currencies)
        return currencies

    }

    fun getBaseCurrencySubject() = mBaseCurrencySubject
    fun getCurrenciesSubject() = mCurrenciesSubject
    fun getShowProgress() = mShowProgress

}