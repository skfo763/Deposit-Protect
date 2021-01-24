package com.skfo763.depositprotect.di

import android.app.Activity
import android.app.Application
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.skfo763.component.tracker.FirebaseTracker
import com.skfo763.remote.NetworkManager
import com.skfo763.storage.AppDataStore
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNetworkManager(application: Application) = NetworkManager()

    @Provides
    @Singleton
    fun provideAppDataStore(application: Application) = AppDataStore(application.applicationContext)

    @Provides
    @Singleton
    fun provideFirebaseTracker(application: Application) = FirebaseTracker(application)

}