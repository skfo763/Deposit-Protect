package com.skfo763.depositprotect.di

import com.skfo763.remote.NetworkManager
import com.skfo763.remote.api.IDepositProtectApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn

@Module
@InstallIn(ActivityModule::class)
object ActivityModule {

    @Provides
    fun provideDepositProtectOpenApi(manager: NetworkManager): IDepositProtectApi =
        manager.getOpenApiRetrofit().create(IDepositProtectApi::class.java)


}