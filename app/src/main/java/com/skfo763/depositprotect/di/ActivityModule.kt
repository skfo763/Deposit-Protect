package com.skfo763.depositprotect.di

import com.skfo763.remote.NetworkManager
import com.skfo763.remote.api.IDepositProtectApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object ActivityModule {

    @Provides
    fun provideDepositProtectOpenApi(manager: NetworkManager): IDepositProtectApi =
        manager.getOpenApiRetrofit().create(IDepositProtectApi::class.java)


}