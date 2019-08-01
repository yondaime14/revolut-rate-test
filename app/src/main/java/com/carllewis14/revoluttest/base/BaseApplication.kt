package com.carllewis14.revoluttest.base

import android.app.Application

class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        Injector.initAppComponent(this)

    }

}