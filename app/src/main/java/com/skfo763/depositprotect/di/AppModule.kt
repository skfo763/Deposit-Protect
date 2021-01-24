package com.skfo763.depositprotect.di

import android.app.Application
import com.skfo763.remote.NetworkManager
import com.skfo763.storage.AppDataStore
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

}