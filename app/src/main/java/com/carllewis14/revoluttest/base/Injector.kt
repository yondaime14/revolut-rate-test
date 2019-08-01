package com.carllewis14.revoluttest.base

import android.app.Application
import com.carllewis14.revoluttest.di.AppComponent
import com.carllewis14.revoluttest.di.DaggerAppComponent
import com.carllewis14.revoluttest.di.module.AppModule
import com.carllewis14.revoluttest.di.module.ViewModelModule

object Injector {

    lateinit var appComponent: AppComponent

    fun initAppComponent(application: Application) {

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(application))
            .viewModelModule(ViewModelModule(application))
            .build()

    }

}