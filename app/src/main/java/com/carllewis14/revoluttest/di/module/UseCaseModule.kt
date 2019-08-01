package com.carllewis14.revoluttest.di.module

import com.carllewis14.revoluttest.network.RevolutRatesApi
import com.carllewis14.revoluttest.usecases.CurrenciesUseCase
import com.carllewis14.revoluttest.usecases.CurrenciesUseCaseImpl
import com.carllewis14.revoluttest.util.concurrency.scheduler.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideCurrenciesUseCase(revolutRatesApi: RevolutRatesApi, schedulerProvider: SchedulerProvider): CurrenciesUseCase
            = CurrenciesUseCaseImpl(revolutRatesApi, schedulerProvider)

}