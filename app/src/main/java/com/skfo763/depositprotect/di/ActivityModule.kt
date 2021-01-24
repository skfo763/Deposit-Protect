package com.skfo763.depositprotect.di

import android.app.Activity
import com.skfo763.component.tracker.FirebaseTracker
import com.skfo763.depositprotect.admob.AdMobManager
import com.skfo763.remote.NetworkManager
import com.skfo763.remote.api.IAppBaseInfoApi
import com.skfo763.remote.impl.AppBaseInfoApi
import com.skfo763.remote.api.IDepositProtectApi
import com.skfo763.repository.IMainRepository
import com.skfo763.repository.MainRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class ActivityModule {

    companion object {
        @Provides
        fun provideDepositProtectOpenApi(manager: NetworkManager): IDepositProtectApi =
            manager.getOpenApiRetrofit().create(IDepositProtectApi::class.java)

        @Provides
        fun provideAppBaseInfoApi(manager: NetworkManager): IAppBaseInfoApi =
            manager.getAppBaseInfoApi()

        @Provides
        fun provideAdmobManager(activity: Activity) = AdMobManager(activity)
    }

    @Binds
    abstract fun bindMainRepository(impl: MainRepository): IMainRepository

}