package com.skfo763.repository

import androidx.paging.PagingData
import com.skfo763.base.theme.ThemeType
import com.skfo763.remote.data.CompanyInfo
import com.skfo763.remote.data.OpenSourceLicense
import com.skfo763.remote.data.ProductInfo
import com.skfo763.repository.data.AppInfo
import com.skfo763.repository.data.DataProvider
import com.skfo763.repository.data.BankData
import com.skfo763.repository.data.Product
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface IMainRepository {

    fun getBaseAppInfo(): Single<AppInfo>

    fun getDataProviderInfo(): Single<DataProvider>

    fun getOpenSourceLicense(): Single<OpenSourceLicense>

    fun getFamousBankList(): Observable<List<BankData>>

    fun setThemeState(themeType: ThemeType): Single<ThemeType>

    fun getBankInfoStream(pageSize: Int = 10, bankName: String? = null): Flowable<PagingData<BankData>>

    fun getProductInfoStream(pageSize: Int = 10, bankName: String? = null, productName: String? = null): Flowable<PagingData<Product>>

}