package com.skfo763.repository

import androidx.paging.PagingData
import com.skfo763.base.theme.ThemeType
import com.skfo763.remote.data.BankShortcut
import com.skfo763.remote.data.CompanyInfo
import com.skfo763.remote.data.OpenSourceLicense
import com.skfo763.remote.data.ProductInfo
import com.skfo763.repository.data.*
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface IMainRepository {

    fun getBaseAppInfo(): Single<AppInfo>

    fun getDataProviderInfo(): Single<DataProvider>

    fun getOpenSourceLicense(): Single<OpenSourceLicense>

    fun getShortcutBankStream(): Single<List<BankShortcutIcon>>

    fun setThemeState(themeType: ThemeType): Single<ThemeType>

    fun getProductInfoStream(pageSize: Int = 10, bankName: String? = null, productName: String? = null): Flowable<PagingData<Product>>

}