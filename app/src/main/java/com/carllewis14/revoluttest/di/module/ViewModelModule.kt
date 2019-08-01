package com.carllewis14.revoluttest.di.module

import android.app.Application
import android.arch.lifecycle.ViewModelProvider.AndroidViewModelFactory
import com.carllewis14.revoluttest.viewmodel.RevolutRatesViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule(private var application: Application) {

    @Provides
    fun provideCurrenciesViewModel() =
        AndroidViewModelFactory.getInstance(application).create(RevolutRatesViewModel::class.java)

}