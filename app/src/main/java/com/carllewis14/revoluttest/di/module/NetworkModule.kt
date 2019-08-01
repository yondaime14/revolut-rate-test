package com.carllewis14.revoluttest.di.module

import com.carllewis14.revoluttest.network.RevolutRatesApi
import com.carllewis14.revoluttest.data.model.names.CurrencyNamesDeserializer
import com.carllewis14.revoluttest.data.model.names.CurrencyNamesResponse
import com.carllewis14.revoluttest.data.model.rates.CurrencyRatesDeserializer
import com.carllewis14.revoluttest.data.model.rates.CurrencyRatesResponse
import com.carllewis14.revoluttest.util.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule() {

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)!!

    @Provides
    fun provideClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient().newBuilder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Provides
    fun provideGson(): Gson =
        GsonBuilder()
            .registerTypeAdapter(CurrencyRatesResponse::class.java, CurrencyRatesDeserializer())
            .registerTypeAdapter(CurrencyNamesResponse::class.java, CurrencyNamesDeserializer())
            .create()

    @Provides
    fun provideRetrofit(client: OkHttpClient, gson: Gson): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.REVOLUT_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @Provides
    fun provideApi(retrofit: Retrofit): RevolutRatesApi =
        retrofit.create<RevolutRatesApi>(RevolutRatesApi::class.java)

}