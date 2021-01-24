package com.skfo763.remote.api

import androidx.annotation.VisibleForTesting
import com.skfo763.remote.data.AppBaseInfo
import com.skfo763.remote.data.DataProviderInfo
import com.skfo763.remote.data.OpenSourceLicense
import com.squareup.moshi.Moshi
import io.reactivex.rxjava3.core.Single

interface IAppBaseInfoApi {

    @VisibleForTesting fun getMoshi(): Moshi

    val appInfo: Single<AppBaseInfo>

    val dataProviderInfo: Single<DataProviderInfo>

    val openSourceLicense: Single<OpenSourceLicense>
}