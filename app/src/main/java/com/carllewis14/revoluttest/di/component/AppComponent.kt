package com.carllewis14.revoluttest.di

import com.carllewis14.revoluttest.di.module.*
import com.carllewis14.revoluttest.ui.CurrenciesAdapter
import com.carllewis14.revoluttest.ui.CurrenciesFragment
import com.carllewis14.revoluttest.viewmodel.RevolutRatesViewModel
import dagger.Component

@Component(modules = [AppModule::class, NetworkModule::class, UseCaseModule::class, UtilModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(revolutRatesViewModel: RevolutRatesViewModel)
    fun inject(ratesAdapter: CurrenciesAdapter)
    fun inject(currenciesFragment: CurrenciesFragment)

}