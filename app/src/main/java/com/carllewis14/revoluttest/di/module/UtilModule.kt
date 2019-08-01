package com.carllewis14.revoluttest.di.module

import android.content.Context
import com.carllewis14.revoluttest.util.code.CodeConverter
import com.carllewis14.revoluttest.util.code.CodeConverterImpl
import com.carllewis14.revoluttest.util.concurrency.scheduler.SchedulerProvider
import com.carllewis14.revoluttest.util.concurrency.scheduler.SchedulerProviderImpl
import com.carllewis14.revoluttest.util.image.ImageLoader
import com.carllewis14.revoluttest.util.image.ImageLoaderImpl
import com.carllewis14.revoluttest.util.log.LogUtil
import com.carllewis14.revoluttest.util.log.LogUtilImpl
import com.carllewis14.revoluttest.util.text.TextUtil
import com.carllewis14.revoluttest.util.text.TextUtilImpl
import dagger.Module
import dagger.Provides

@Module
class UtilModule {

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = SchedulerProviderImpl()

    @Provides
    fun provideImageLoader(context: Context): ImageLoader = ImageLoaderImpl(context)

    @Provides
    fun provideCodeConverter(): CodeConverter = CodeConverterImpl()

    @Provides
    fun provideTextUtil(): TextUtil = TextUtilImpl()

    @Provides
    fun provideLogUtil(): LogUtil = LogUtilImpl()

}