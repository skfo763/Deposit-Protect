package com.skfo763.depositprotect.di

import android.app.Activity
import com.skfo763.depositprotect.admob.AdMobManager
import com.skfo763.remote.NetworkManager
import com.skfo763.remote.api.IAppBaseInfoApi
import com.skfo763.remote.api.IBankInfoApi
import com.skfo763.remote.api.IDepositProtectApi
import com.skfo763.repository.IBankSelectRepository
import com.skfo763.repository.IMainRepository
import com.skfo763.repository.impl.BankSelectRepository
import com.skfo763.repository.impl.MainRepository
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
        fun provideDepositProtectPagingSource(manager: NetworkManager): IDepositProtectApi =
            manager.getOpenApiRetrofit().create(IDepositProtectApi::class.java)

        @Provides
        fun provideAppBaseInfoApi(manager: NetworkManager): IAppBaseInfoApi =
            manager.getAppBaseInfoApi()

        @Provides
        fun provideBankInfoApi(manager: NetworkManager): IBankInfoApi =
            manager.getBankInfoApi()

        @Provides
        fun provideAdmobManager(activity: Activity) = AdMobManager(activity)
    }

    @Binds
    abstract fun bindMainRepository(impl: MainRepository): IMainRepository

    @Binds
    abstract fun bindBankSelectRepository(impl: BankSelectRepository): IBankSelectRepository
}