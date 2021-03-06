package com.skfo763.remote

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.skfo763.remote.api.IAppBaseInfoApi
import com.skfo763.remote.api.IBankInfoApi
import com.skfo763.remote.impl.AppBaseInfoApi
import com.skfo763.remote.impl.BankInfoApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class NetworkManager {

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val firestore = Firebase.firestore

    fun getOpenApiRetrofit(): Retrofit = Retrofit.Builder().baseUrl(BuildConfig.OPEN_API_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .client(OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addNetworkInterceptor(StethoInterceptor())
            .apply {
                if(BuildConfig.DEBUG) {
                    addInterceptor(HttpLoggingInterceptor().apply {
                        this.level = HttpLoggingInterceptor.Level.BODY
                    })
                }
            }.build()
        ).build()


    fun getAppBaseInfoApi(): IAppBaseInfoApi = AppBaseInfoApi(moshi, firestore)

    fun getBankInfoApi(): IBankInfoApi = BankInfoApi(moshi, firestore)
}